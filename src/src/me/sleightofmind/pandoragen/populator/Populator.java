package me.sleightofmind.pandoragen.populator;

import java.util.List;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;

public abstract class Populator {
	
	public static void populate(World w, Random r, Chunk c, List<Integer> biomes) {
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().equals(this.getClass())) {
			return true;
		} else {
			return false;
		}
	}
	
}
