package me.sleightofmind.pandoragen.populator;

import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.Util;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class PandoraBlockPopulator extends BlockPopulator {

	@Override
	public void populate(World w, Random r, Chunk c) {
		List<Integer> biomes = Util.getApplicableBiomes(c);
		LandmassPopulator.populate(w, r, c, biomes);
		StructurePopulator.populate(w, r, c, biomes);
		VegetationPopulator.populate(w, r, c, biomes);
		
	}

}
