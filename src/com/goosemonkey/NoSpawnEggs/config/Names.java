package com.goosemonkey.NoSpawnEggs.config;

public enum Names
{
	EID50("Creeper"),
	EID51("Skeleton"),
	EID52("Spider"),
	EID54("Zombie"),
	EID55("Slime"),
	EID56("Ghast"),
	EID57("Zombie Pigman"),
	EID58("Enderman"),
	EID59("Cave Spider"),
	EID60("Silverfish"),
	EID61("Blaze"),
	EID62("Magma Cube"),
	EID90("Pig"),
	EID91("Sheep"),
	EID92("Cow"),
	EID93("Chicken"),
	EID94("Squid"),
	EID95("Wolf"),
	EID96("Mooshroom"),
	EID120("Villager"),
	NO_EGG_PERM("You don't have permission to spawn this %s."),
	NO_GOLEM_PERM("You don't have permission to create Snow Golems."),
	NO_RELOAD_PERM("You don't have permission to reload the NoSpawnEggs config."),
	RELOADED("NoSpawnEggs config reloaded."),
	NO_CHICKEN_EGG_PERM("You don't have permission to spawn Chickens with eggs.");
	
	private final String text;

    private Names(String def)
    {
        text = def;
    }

    public String toString()
    {
        return text;
    }
}
