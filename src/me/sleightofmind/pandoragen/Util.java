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
	
	/**
	 * Utility method to set a block's ID within the blockdata[][] array for a chunk
	 * @param x x-coordinate within chunk
	 * @param z z-coordinate within chunk
	 */
	public static void setBlock(short[][] blockdata, int x, int y, int z, short blkid) {
        if (blockdata[y >> 4] == null) {
            blockdata[y >> 4] = new short[4096];
        }
        blockdata[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
    }
}
