package com.goosemonkey.NoSpawnEggs;

public enum EntityType {

	CREEPER, SKELETON, SPIDER, ZOMBIE, SLIME, GHAST, PIGZOMBIE, ENDERMAN,
	CAVESPIDER, SILVERFISH, BLAZE, MAGMACUBE, PIG, SHEEP, COW, CHICKEN, 
	SQUID, WOLF, MOOSHROOM, VILLAGER, MISSINGNO;
	
	public String toString()
	{
		return this.name().toLowerCase();
	}	
}
