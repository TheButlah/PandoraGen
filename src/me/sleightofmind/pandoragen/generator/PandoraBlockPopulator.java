package me.sleightofmind.pandoragen.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.sleightofmind.pandoragen.Util;
import me.sleightofmind.pandoragen.biomes.Biome;
import me.sleightofmind.pandoragen.populator.Populator;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class PandoraBlockPopulator extends BlockPopulator {

	@Override
	public void populate(World w, Random r, Chunk c) {
		List<Biome> biomes = Util.getApplicableBiomes(c);
		List<Populator> populators = new ArrayList<Populator>();
		for (Biome b : biomes) {
			for (Populator p : b.getPopulators()) {
				if (populators.contains(p)) continue;
				populators.add(p);
			}
		}
	}

}
