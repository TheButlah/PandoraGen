package me.sleightofmind.pandoragen.populator;

import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.biomes.PlainsBiome;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

public class ShrubberyPopulator extends Populator{
	
	public static void populate(World w, Random r, Chunk c, List<Integer> biomes) {
		if(biomes.contains(PlainsBiome.getID())){
			for(int i = 0; i < 4; i++){
				int x = r.nextInt(16);
				int z = r.nextInt(16);
				w.getHighestBlockAt(x, z).setType(Material.LONG_GRASS);
			}
		}
	}

}
