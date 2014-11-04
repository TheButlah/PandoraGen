package me.sleightofmind.pandoragen;

import java.util.ArrayList;
import java.util.List;

import me.sleightofmind.pandoragen.biomes.Biome;

import org.bukkit.Chunk;

public class Util {
	/**
	 * NOTE: Will not check every block, but instead every 4 blocks. Total of 16 checks per chunk instead of 256.
	 */
	public static List<Biome> getApplicableBiomes(Chunk c) {
		List<Biome> result = new ArrayList<Biome>();
		for (int x=0; x<16; x+=4) {
			for (int z=0; z<16; z+=4) {
				for (Biome b : PandoraGen.wman.getApplicableBiomesByLocation(x + c.getX()<<4, z + c.getZ()<<4)) {
					if (result.contains(b)) continue;
					result.add(b);
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
