package me.sleightofmind.pandoragen.populator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

public class OrePopulator extends BlockPopulator{

	@Override
	public void populate(World world, Random rand, Chunk ch) {
		int[] ores = {Material.COAL_ORE.getId(), Material.IRON_ORE.getId(), Material.LAPIS_ORE.getId(), Material.GOLD_ORE.getId(), Material.DIAMOND_ORE.getId(), Material.REDSTONE_ORE.getId(), Material.EMERALD_ORE.getId()};
		int[] immersedblock = {1,1,1,1,1,1,1};
		
		int[] maxheight = {128, 64, 23, 29, 12, 12, 29};
		int[] minheight = {0,0,0,0,0,0,0};
		
		int[] minveins = {20, 9, 0, 0, 0, 3, -1};
		int[] maxveins = {24, 13, 2, 2, 1, 5, 1};
		
		
		int[] minvsize = {3, 4, 1, 2 /*NV*/, 4, 4, 1};
		int[] maxvsize = {10, 10, 8, 8, 8, 8, 1};
		
		
		
		
		for(int oi = 0; oi < ores.length; oi++){
			int numveins = maxveins[oi] == minveins[oi] ? minveins[oi] : rand.nextInt(maxveins[oi] - minveins[oi] + 1) + minveins[oi];
			for(int nvi = 0; nvi < numveins * 2 && numveins > 0; nvi++){
				int x = rand.nextInt(16);
				int z = rand.nextInt(16);
				int y = (maxheight[oi] == minheight[oi] ? minheight[oi] : rand.nextInt(maxheight[oi] - minheight[oi] + 1) + minheight[oi]);
				if(ch.getBlock(x, y, z).getTypeId() == immersedblock[oi]){
					Block ore = ch.getBlock(x, y, z);
					
					int numores = (maxvsize[oi] == minvsize[oi] ? minvsize[oi] : rand.nextInt(maxvsize[oi] - minvsize[oi] + 1) + minvsize[oi]);
					this.genVein(ores[oi], numores, immersedblock[oi], numores, ore, rand, ch, BlockFace.EAST_NORTH_EAST);
					numveins--;
				}
			}
		}
		
		
		
	}
	
	public void genVein(int oreid, int numores, int immersedmat, int size, Block ore, Random rand, Chunk ch, BlockFace previousmove){
		ore.setTypeId(oreid);
		numores--;
		ArrayList<BlockFace> faces = new ArrayList<BlockFace>(); 
		faces.addAll(Arrays.asList(BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.EAST, BlockFace.UP, BlockFace.DOWN));
		faces.remove(previousmove);
		faces.remove(previousmove.getOppositeFace());
		while(faces.size() > 0 && numores > 0){
			int i = rand.nextInt(faces.size());
			if(ore.getRelative(faces.get(i)).getTypeId() == immersedmat){
				genVein(oreid, numores, immersedmat, size, ore.getRelative(faces.get(i)), rand, ch, faces.get(i));
				return;
			}else{
				faces.remove(i);
			}
		}
	}

}
