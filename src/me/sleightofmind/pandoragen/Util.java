package me.sleightofmind.pandoragen;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;

public class Util {
	public static List<Integer> getApplicableBiomes(Chunk c){
		List<Integer> result = new ArrayList<Integer>();
		int[][] blocks = {{0,0},{7,0},{15,0},{0,7},{7,7},{15,7},{0,15},{7,15},{15,15}};
		for(int[] loc : blocks){
			List<Integer> biomes = PandoraGen.wman.getApplicableBiomes(getCoordinate(c.getX(), loc[0]), getCoordinate(c.getX(), loc[1]));
			for(Integer id : biomes){
				if(!result.contains(id)){
					result.add(id);
				}
			}
		}
		return result;
	}
	
	public static int getCoordinate(int chunkx, int x){
		return (chunkx << 4) + x;
	}
}
