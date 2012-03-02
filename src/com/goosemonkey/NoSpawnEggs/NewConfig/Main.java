package com.goosemonkey.NoSpawnEggs.NewConfig;

import java.util.Arrays;

import org.bukkit.plugin.java.JavaPlugin;

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
				"This is the main configuration of the plugin.\n\n" +
				"" +
				"allowAllSpawns: For animal, monster, and npc options, this can disable the spawner\n" +
				"egg blocking of a certain type of entity. For unknown, this disables the blocking\n" +
				"of spawning Entities unknown to the plugin (not recommended).\n\n" +
				
				"allowAllSpawns.perWorld: Set per-world configurations here, just as the model, \n" +
				"replacing the \"ExampleWorld\" with whatever the world is named. Per-world configuration\n" +
				"overrides global configuration. Blocked and Allowed IDs override all per-world and global\n" +
				"configuration. Get entity IDs on the wiki. If you have any questions on how to accomplish\n" +
				"something, post a comment on the BukkitDev page.\n\n" +
				
				"snowGolemBlocking/ironGolemBlocking: Enables the blocking of Snow/Iron Golems. Worlds in \"ignoredWorlds\"\n" +
				"will not be affected by this.\n\n" +
				
				"chickenEggBlocking.eggThrowBlocking: Blocks people from spam-spawning chickens. Can be set\n" +
				"to only trigger on people in creative mode. Should it send a message to players when\n" +
				"denied a chicken egg spawn? Worlds in ignoredWorlds will not be affected.\n\n" +
				"chickenEggBlocking.dispenseBlocking: Prevents a way to overcome chicken egg\n" +
				"throw blocking by disallowing dispensers to spawn chickens in certain worlds.\n\n" +
				
				"spawnEggDispenseBlocking: Prevents players from using dispensers to spawn mobs (Added\n" +
				"MC 1.2). Worlds on ignoredWorlds will not be affected.\n\n" +
				
				"expBottleBlocking: Prevents players from being able to throw Bottles of Enchanting.\n" +
				"As of MC 1.2, experience isn't required to enchant in creative, so this won't effect enchanting much.\n\n" +
				
				"fireChargeDispenseBlocking: Prevents dispensers from shooting fire charges (Added MC\n" +
				"1.2), to combat griefing and lag." +
				
//				"timer: Allow users to override the blocking once every x seconds, defined here.\n" +
//				"A user must have the permission nospawneggs.timer.[egg/golem/chickenegg] for the timer\n" +
//				"to apply to them. If a timer is to be disabled, set the time to -1." +
				"";
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
		checkSet("snowGolemBlocking.onlyCreative", true);
		checkSet("snowGolemBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
		checkSet("ironGolemBlocking.enable", true);
		checkSet("ironGolemBlocking.onlyCreative", true);
		checkSet("ironGolemBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
		checkSet("chickenEggBlocking.eggThrowBlocking.enable", true);
		checkSet("chickenEggBlocking.eggThrowBlocking.onlyCreative", true);
		checkSet("chickenEggBlocking.eggThrowBlocking.sendMessage", true);
		checkSet("chickenEggBlocking.eggThrowBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("chickenEggBlocking.dispenseBlocking.enable", true);
		checkSet("chickenEggBlocking.dispenseBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
		checkSet("spawnEggDispenseBlocking.enable", true);
		checkSet("spawnEggDispenseBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));

		checkSet("expBottleBlocking.enable", true);
		checkSet("expBottleBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("expBottleBlocking.onlyCreative", true);
		
		checkSet("fireChargeDispenseBlocking.enable", true);
		checkSet("fireChargeDispenseBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		
//		checkSet("timer.spawnerEggs", 30);
//		checkSet("timer.snowGolems", -1);
//		checkSet("timer.chickenEggs", 10);
	}
}
