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
	 * NOTE: FILES SHOULD DESCRIBE BIOMES IN THE ORDER THAT THE BIOMES APPEAR IN THE PandoraGen.biomes array
	 * @param numbiomes Total number of biomes
	 * @param diagramDirectory Folder where Whittaker Diagrams are stored
	 * @param resolution Resolution of the Whittaker Diagram RAW files
	 */
	public WhittakerManager(int numbiomes, String diagramDirectory, int resolution) {
		this.numbiomes = numbiomes;
		this.diagramDirectory = diagramDirectory;
		this.resolution = resolution;
		
		whittakerdiagram = new byte[numbiomes][resolution][resolution];
		for (int i = 0; i<Math.ceil(numbiomes/3.0); i++) {
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
			
			for (int y = 0; y<resolution; y++) {
				for (int x = 0; x<resolution; x++) {
					red[x][y] = data[(y*resolution + x)*3];
					green[x][y] = data[(y*resolution + x)*3+1];
					blue[x][y] = data[(y*resolution + x)*3+2];
				}
			}
			
			switch((numbiomes-1)%3) {
			case 0:
				whittakerdiagram[i*3] = red;
				break;
			case 1:
				whittakerdiagram[i*3] = red;
				whittakerdiagram[i*3+1] = green;
				break;
			case 2:
				whittakerdiagram[i*3] = red;
				whittakerdiagram[i*3+1] = green;
				whittakerdiagram[i*3+2] = blue;
				break;
			}
		}
	}
	
	/**
	 * @param biomeid ID of biome in the Biomes array
	 * @param xcoord X Location
	 * @param zcoord Z Location
	 * @return Dominance from 0 - 255 inclusive
	 */
	public int getDominanceByLocation(Biome biome, int xcoord, int zcoord){
		return whittakerdiagram[biome.getID()][getTemperature(xcoord,zcoord)][getHumidity(xcoord,zcoord)] & 0xFF;
	}
	
	public List<Biome> getApplicableBiomes(int xcoord, int zcoord){
		List<Biome> result = new ArrayList<Biome>();
		for(int i = 0; i < numbiomes; i++){
			int humidity = getHumidity(xcoord, zcoord);
			int temperature = getTemperature(xcoord, zcoord);
			Biome current = PandoraGen.biomes.get(i);
			if(getDominanceByAtmosphere(current, temperature, humidity) > 0){
				result.add(current);
			}
		}
		return result;
	}
	
	/**
	 * @param biomeid ID of biome in the Biomes array
	 * @return Dominance from 0 - 255 inclusive
	 */
	public int getDominanceByAtmosphere(Biome biome, int temperature, int humidity){
		return whittakerdiagram[biome.getID()][temperature][humidity] & 0xFF;
	}
	
	public int getTemperature(int x, int z){
		return 0; //TODO: Make these methods actually work.
	}
	
	public int getHumidity(int x, int z){
		return 0; //TODO: Make these methods actually work.
	}
	
}
