# PandoraGen
This plugin is a custom terrain generator for CraftBukkit/Spigot Minecraft servers. It hooks into the Bukkit API for Minecraft and has a custom implementation of terrain generation. 
The plugin uses a coherent noise function to generate much of the terrain, and has it's own internal system to select the relevant biomes to use and interpolate between them on biome boundaries. Each biome can have its own method of generating both terrain and special features.
