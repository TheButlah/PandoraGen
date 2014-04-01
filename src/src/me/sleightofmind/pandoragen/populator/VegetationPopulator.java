package me.sleightofmind.pandoragen.populator;

import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;

public class VegetationPopulator extends Populator {
	
	
	public void populate(World w, Random r, Chunk c, List<Integer> biomes) {
		ShrubberyPopulator.populate(w, r, c, biomes);
		TreePopulator.populate(w, r, c, biomes);
	}
}