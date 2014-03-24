package me.sleightofmind.pandoragen.biomes;

public enum Biomes {

	DEFAULT(0), PLAINS(1);
	
	private int id;
		
	Biomes(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}
