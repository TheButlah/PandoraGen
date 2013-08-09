package me.thebutlah.pandoragen;

import me.thebutlah.pandoragen.generator.PandoraChunkGenerator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class PandoraGen extends JavaPlugin {
	
	public final static String WORLD_NAME = "Pandora";
	private static World world = null;
	
	@Override
	public void onEnable() {
		
	}
	
	/**
	 * @return The world if it exists, otherwise it generates the world and then returns it.
	 */
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id){
		return new PandoraChunkGenerator();
	}
	
	
	public static World getWorld() {
		
		WorldCreator wc = new WorldCreator(WORLD_NAME);
		wc.environment(Environment.NORMAL);
		wc.generateStructures(false);
		wc.generator(new PandoraChunkGenerator());
		
		if (world == null) {
			world = Bukkit.createWorld(wc);
		}
		return world;
	}

}
