package me.sleightofmind.pandoragen.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.Util;
import me.sleightofmind.pandoragen.populator.LandmassPopulator;
import me.sleightofmind.pandoragen.populator.Populator;
import me.sleightofmind.pandoragen.populator.StructurePopulator;
import me.sleightofmind.pandoragen.populator.VegetationPopulator;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class PandoraBlockPopulator extends BlockPopulator {

	@Override
	public void populate(World w, Random r, Chunk c) {
		List<Integer> biomes = Util.getApplicableBiomes(c);
		List<Populator> populators = new ArrayList<Populator>();
		for (Integer i : biomes) {
			
		}
		LandmassPopulator.populate(w, r, c, biomes);
		StructurePopulator.populate(w, r, c, biomes);
		VegetationPopulator.populate(w, r, c, biomes);
		
	}

}
