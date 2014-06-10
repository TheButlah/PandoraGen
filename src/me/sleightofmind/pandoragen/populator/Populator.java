package me.sleightofmind.pandoragen.populator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;

public interface Populator {
	
	public String getName();
	
	public final ArrayList<String> biomes = new ArrayList<String>();
	
	public void populate(World w, Random r, Chunk c, List<Integer> biomes);

}
