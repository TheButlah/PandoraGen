package me.sleightofmind.pandoragen.populator;

import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;

public interface Populator {
	
	public static String name = "";
	
	public void populate(World w, Random r, Chunk c, List<Integer> biomes);

}
