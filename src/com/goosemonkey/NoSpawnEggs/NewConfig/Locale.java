package com.goosemonkey.NoSpawnEggs.NewConfig;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Locale.yml Object
 * @author GooseMonkey97
 */
public class Locale extends Config
{
	private Locale (JavaPlugin inst, String fileName)
	{
		super(inst, fileName);
	}
	
	public Locale (JavaPlugin inst)
	{
		this (inst, "Locale.yml");
	}
	
	public String getHeader()
	{
		return
			"\n" +
			"Set custom messages here, for language translations or just to make them better.\n\n" +
			"reloadedMessage: Sent to a player when successfully reloading the config (/nospawneggs reload)\n" +
			"noReloadPerms: When the player attempts to reload, but can't.\n" +
			"noSpawnerEggPerms: When you a player can't spawn a mob via egg. Replace %s with the mob's name.\n" +
			"noSnowGolemPerms/noIronGolemPerms/noChickenEggPerms/noExpBottlePerms: It's not that hard to figure out. Is it?\n" +
			"" ;
			
//			"timerMessage: When the player needs to wait before doing this action again."
	}
	
	public void setDefaultValues()
	{
		FileConfiguration f = this.getConfig();
		
		if (!f.isSet("entity.EID0"))
		f.set("entity.EID0", "Entity");
		
		if (!f.isSet("entity.EID50"))
		f.set("entity.EID50", "Creeper");
		
		if (!f.isSet("entity.EID51"))
		f.set("entity.EID51", "Skeleton");

		if (!f.isSet("entity.EID52"))
		f.set("entity.EID52", "Spider");

		if (!f.isSet("entity.EID54"))
		f.set("entity.EID54", "Zombie");

		if (!f.isSet("entity.EID55"))
		f.set("entity.EID55", "Slime");

		if (!f.isSet("entity.EID56"))
		f.set("entity.EID56", "Ghast");

		if (!f.isSet("entity.EID57"))
		f.set("entity.EID57", "Zombie Pigman");

		if (!f.isSet("entity.EID58"))
		f.set("entity.EID58", "Enderman");

		if (!f.isSet("entity.EID59"))
		f.set("entity.EID59", "Cave Spider");

		if (!f.isSet("entity.EID60"))
		f.set("entity.EID60", "Silverfish");

		if (!f.isSet("entity.EID61"))
		f.set("entity.EID61", "Blaze");
		
		if (!f.isSet("entity.EID62"))
		f.set("entity.EID62", "Magma Cube");
		
		if (!f.isSet("entity.EID90"))
		f.set("entity.EID90", "Pig");

		if (!f.isSet("entity.EID91"))
		f.set("entity.EID91", "Sheep");

		if (!f.isSet("entity.EID92"))
		f.set("entity.EID92", "Cow");

		if (!f.isSet("entity.EID93"))
		f.set("entity.EID93", "Chicken");

		if (!f.isSet("entity.EID94"))
		f.set("entity.EID94", "Squid");

		if (!f.isSet("entity.EID95"))
		f.set("entity.EID95", "Wolf");

		if (!f.isSet("entity.EID96"))
		f.set("entity.EID96", "Mooshroom");
		
		checkSet("entity.EID98", "Ocelot");

		if (!f.isSet("entity.EID120"))
		f.set("entity.EID120", "Villager");
		
		
		if (!f.isSet("reloadedMessage"))
		f.set("reloadedMessage", "NoSpawnEggs config reloaded.");

		if (!f.isSet("noReloadPerms"))
		f.set("noReloadPerms", "You don't have permission.");

		if (!f.isSet("noSpawnerEggPerms"))
		f.set("noSpawnerEggPerms", "You don't have permission to spawn this %s.");

		if (!f.isSet("noSnowGolemPerms"))
		f.set("noSnowGolemPerms", "You don't have permission to create Snow Golems.");
		
		checkSet("noIronGolemPerms", "You don't have permission to create Iron Golems.");

		if (!f.isSet("noChickenEggPerms"))
		f.set("noChickenEggPerms", "You don't have permission to spawn Chickens from eggs.");
		
		checkSet("noExpBottlePerm", "You don't have pemission to spawn Experience Orbs.");
		
		checkSet("noEnderEyePerm", "You don't have permission to throw Eyes of Ender.");
		
		checkSet("noEnderPearlPerm", "You don't have permission to throw Ender Pearls.");
		
		checkSet("noBoatPerm", "You don't have permission to create boats.");
		
		checkSet("noMinecartPerm", "You don't have permission to create minecarts.");

		
//		if (!f.isSet("timerMessage"))
//		f.set("timerMessage", "You have to wait a while before you can do this again.");
	}
}
