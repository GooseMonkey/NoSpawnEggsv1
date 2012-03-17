package com.goosemonkey.NoSpawnEggs.NewConfig;

import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * MainConfig.yml Object
 * @author GooseMonkey97
 */
public class Main extends Config
{
	private Main (JavaPlugin inst, String fileName)
	{
		super(inst, fileName);
	}
	
	public Main (JavaPlugin inst)
	{
		this (inst, "MainConfig.yml");
	}
	
	public String getHeader()
	{
		return
				"\n" +
				"For help: www.tinyurl.com/nseconfig" +
				"\n";
	}
	
	public void setDefaultValues()
	{
		checkSet("allowAllSpawns.monster", false);
		checkSet("allowAllSpawns.animal", false);
		checkSet("allowAllSpawns.npc", false);
		checkSet("allowAllSpawns.unknown", false);
		checkSet("allowAllSpawns.perWorld.ExampleWorld.animal", true);
		checkSet("allowAllSpawns.perWorld.ExampleWorld.npc", true);
		checkSet("allowAllSpawns.perWorld.ExampleWorld.allowedIds", Arrays.asList(new Integer[]{50}));
		checkSet("allowAllSpawns.perWorld.ExampleWorld.blockedIds", Arrays.asList(new Integer[]{51}));
		
		checkSet("snowGolemBlocking.enable", true);
		checkSet("snowGolemBlocking.onlyCreative", false);
		checkSet("snowGolemBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
		checkSet("ironGolemBlocking.enable", true);
		checkSet("ironGolemBlocking.onlyCreative", false);
		checkSet("ironGolemBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
		checkSet("chickenEggBlocking.eggThrowBlocking.enable", true);
		checkSet("chickenEggBlocking.eggThrowBlocking.onlyCreative", false);
		checkSet("chickenEggBlocking.eggThrowBlocking.sendMessage", true);
		checkSet("chickenEggBlocking.eggThrowBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("chickenEggBlocking.dispenseBlocking.enable", true);
		checkSet("chickenEggBlocking.dispenseBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
		checkSet("spawnEggDispenseBlocking.enable", true);
		checkSet("spawnEggDispenseBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));

		checkSet("expBottleBlocking.enable", true);
		checkSet("expBottleBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("expBottleBlocking.onlyCreative", false);
		
		checkSet("fireChargeDispenseBlocking.enable", true);
		checkSet("fireChargeDispenseBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
		checkSet("expBottleBlocking.dispenseBlocking.enable", true);
		checkSet("expBottleBlocking.dispenseBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
		checkSet("enderBlocking.blockEnderPearl", true);
		checkSet("enderBlocking.enderPearlIgnoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("enderBlocking.blockEnderEye", true);
		checkSet("enderBlocking.enderEyeIgnoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("enderBlocking.eyeOnlyBlockCreative", false);
		
		checkSet("boatBlocking.enable", true);
		checkSet("boatBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("boatBlocking.onlyCreative", false);
		
		checkSet("minecartBlocking.enable", true);
		checkSet("minecartBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("minecartBlocking.onlyCreative", false);
		
//		checkSet("timer.spawnerEggs", 30);
//		checkSet("timer.snowGolems", -1);
//		checkSet("timer.chickenEggs", 10);
	}
}
