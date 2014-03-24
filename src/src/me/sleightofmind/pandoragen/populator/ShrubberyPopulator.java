package me.sleightofmind.pandoragen.populator;

import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.biomes.Biomes;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

public class ShrubberyPopulator extends Populator{
	
	public static void populate(World w, Random r, Chunk c, List<Integer> biomes) {
		//get all asociated
		
		if(biomes.contains(Biomes.PLAINS.getID())){
			for(int i = 0; i < 4; i++){
				int x = r.nextInt(16);
				int z = r.nextInt(16);
				
				w.getHighestBlockAt(x, z).setType(Material.LONG_GRASS);
			}
		}
	}

}
