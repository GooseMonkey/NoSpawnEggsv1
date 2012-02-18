package com.goosemonkey.NoSpawnEggs.NewConfig;

import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;
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
				"snowGolemBlocking: Enables the blocking of Snow Golems. Worlds in \"ignoredWorlds\"\n" +
				"will not be affected by this.\n\n" +
				"chickenEggBlocking.eggThrowBlocking: Blocks people from spam-spawning chickens. Can be set\n" +
				"to only trigger on people in creative mode. Should it send a message to players when\n" +
				"denied a chicken egg spawn? Worlds in ignoredWorlds will not be affected.\n\n" +
				"chickenEggBlocking.dispenseBlocking: Prevents a way to overcome chicken egg\n" +
				"throw blocking by disallowing dispensers to spawn chickens in certain worlds.\n" +
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
		
		checkSet("chickenEggBlocking.eggThrowBlocking.enable", true);
		checkSet("chickenEggBlocking.eggThrowBlocking.onlyCreative", true);
		checkSet("chickenEggBlocking.eggThrowBlocking.sendMessage", true);
		checkSet("chickenEggBlocking.eggThrowBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));
		checkSet("chickenEggBlocking.dispenseBlocking.enable", true);
		checkSet("chickenEggBlocking.dispenseBlocking.ignoredWorlds", Arrays.asList(new String[]{"ExampleWorld"}));

	}
	
	public void checkSet(String path, Object def)
	{
		FileConfiguration f = this.getConfig();
		
		if (!f.isSet(path))
		{
			f.set(path, def);
		}
	}
}