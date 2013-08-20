package me.sleightofmind.pandoragen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WhittakerManager {
	
	public static final String diagramDirectory = "C:\\Users\\Ryan\\Desktop\\";
	public static final int resolution = 2;
	
	public WhittakerManager() {
		for (int i = 0; i<Math.ceil(PandoraGen.biomes.size()/3.0); i++) {
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
			
			PandoraGen.biomes.get(i*3).whittakerMap = red;
			if ((PandoraGen.biomes.size() -1)  % 3 == 1) {
				PandoraGen.biomes.get(i*3 + 1).whittakerMap = green;
			} else if ((PandoraGen.biomes.size()-1) % 3 == 2) {
				PandoraGen.biomes.get(i*3 + 1).whittakerMap = green;
				PandoraGen.biomes.get(i*3 + 2).whittakerMap = blue;
			}
		}
	}

}
