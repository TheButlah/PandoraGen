package me.sleightofmind.pandoragen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import me.sleightofmind.pandoragen.biomes.Biome;

public class WhittakerManager {
	
	public String diagramDirectory;
	public int resolution;
	private int numbiomes;
	
	private byte[][][] whittakerdiagram; //biome, x, y
	
	/**
	 * Loads Whittaker Diagrams from files and stores them.
	 * NOTE: FILES SHOULD DESCRIBE BIOMES IN THE ORDER THAT THE BIOMES APPEAR IN THE PandoraGen.biomes array.
	 * Files should also be in the form of 0.raw, 1.raw, 2.raw, etc.
	 * @param numbiomes Total number of biomes
	 * @param diagramDirectory Folder where Whittaker Diagrams are stored
	 * @param resolution Resolution of the Whittaker Diagram RAW files
	 */
	public WhittakerManager(int numbiomes, String diagramDirectory, int resolution) {
		this.numbiomes = numbiomes;
		this.diagramDirectory = diagramDirectory;
		this.resolution = resolution;
		
		whittakerdiagram = new byte[numbiomes][resolution][resolution];
		int numfiles = (int) Math.ceil(numbiomes/3.0);
		for (int i = 0; i<numfiles; i++) {
			//finds and loads the data from the respective raw files into red, green, and blue arrays
			File diagramfile = new File(diagramDirectory + (i+1) + ".raw");
			byte[] data = null;
			try {
				data = Files.readAllBytes(diagramfile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[][] red = new byte[resolution][resolution];
			byte[][] green = new byte[resolution][resolution];
			byte[][] blue = new byte[resolution][resolution];
			
			//copy raw data into color arrays
			for (int y = 0; y<resolution; y++) {
				for (int x = 0; x<resolution; x++) {
					red[x][y] = data[(y*resolution + x)*3];
					green[x][y] = data[(y*resolution + x)*3+1];
					blue[x][y] = data[(y*resolution + x)*3+2];
				}
			}
			
			//AKA if on last file
			if (i == numfiles - 1) {
				switch(numfiles % 3) {
				case 0:
					whittakerdiagram[i*3] = red;
					whittakerdiagram[i*3+1] = green;
					whittakerdiagram[i*3+2] = red;
					break;
				case 1:
					whittakerdiagram[i*3] = red;
					break;
				case 2:
					whittakerdiagram[i*3] = red;
					whittakerdiagram[i*3+1] = green;
					break;
				}
			} else {
				whittakerdiagram[i*3] = red;
				whittakerdiagram[i*3+1] = green;
				whittakerdiagram[i*3+2] = red;
			}			
		}
	}
	
	/**
	 * @param biome Biome in the Biomes array to check the dominance of
	 * @param xcoord X Coordinate
	 * @param zcoord Z Coordinate
	 * @return Dominance from 0 - 255 inclusive
	 */
	public int getDominanceByLocation(Biome biome, int xcoord, int zcoord){
		//bitwise AND so that values map back into 0-255
		return whittakerdiagram[biome.getID()][getTemperature(xcoord,zcoord)][getHumidity(xcoord,zcoord)] & 0xFF;
	}
	
	/**
	 * @param biome Biome in the Biomes array to check the dominance of
	 * @return Dominance from 0 - 255 inclusive
	 */
	public int getDominanceByAtmosphere(Biome biome, int temperature, int humidity){
		return whittakerdiagram[biome.getID()][temperature][humidity] & 0xFF;
	}
	
	public List<Biome> getApplicableBiomesByLocation(int xcoord, int zcoord){
		
		List<Biome> result = new ArrayList<Biome>();
		int humidity = getHumidity(xcoord, zcoord);
		int temperature = getTemperature(xcoord, zcoord);

		for(int i = 0; i < numbiomes; i++){
			Biome current = PandoraGen.biomes.get(i);
			if(getDominanceByAtmosphere(current, temperature, humidity) > 0){
				result.add(current);
			}
		}
		return result;
	}
	
	public int getTemperature(int xcoord, int zcoord){
		return 0; //TODO: Make these methods actually work.
	}
	
	public int getHumidity(int xcoord, int zcoord){
		return 0; //TODO: Make these methods actually work.
	}
	
}
