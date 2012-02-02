package com.goosemonkey.NoSpawnEggs;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class NoSpawnEggs extends JavaPlugin {

	public PluginDescriptionFile pdf;
	public Logger log;
	
	private NSEPerms perm;
	public CustomNames names;
	
	public void onEnable() 
	{		
		//initialize vars
		pdf = this.getDescription();
		log = this.getServer().getLogger();
		perm = new NSEPerms(this);
		
		this.getConfig();
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().header(header);
		this.saveConfig();
		
		this.names = new CustomNames(this);

		PlayerEggThrowListener petl = new PlayerEggThrowListener(this);
		this.getServer().getPluginManager().registerEvents(petl, this);
		
//		PlayerPumpkinListener ppl = new PlayerPumpkinListener(this);
//		this.getServer().getPluginManager().registerEvents(ppl, this); DISABLED UNTIL 1.1R4?
		
		log.info("NoSpawnEggs v"+pdf.getVersion()+" enabled!");
	}

	public void onDisable()
	{
		this.saveConfig();
		this.names.saveYamls();
		log.info("NoSpawnEggs v"+pdf.getVersion()+" disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof ConsoleCommandSender)
		{
			this.reloadConfig();
			names.loadYamls();
			sender.sendMessage("§eNoSpawnEggs config and names reloaded.");
			return true;
		}
		
		Player player = null;
		
		try
		{
			player = (Player) sender;
		}
		catch (Exception e)
		{
			return false;
		}
		
		if (getPermHandler().hasPermission(player, "nospawneggs.reload") || player.isOp())
		{
			this.reloadConfig();
			player.sendMessage("§eNoSpawnEggs config reloaded.");
			return true;
		}
		else
		{
			player.sendMessage("§eYou don't have permission to reload the config.");
			return true;
		}
	}
	
	public NSEPerms getPermHandler()
	{
		return perm;
	}
	
	private String header = 
			"NoSpawnEggs config file. http://dev.bukkit.org/server-mods/nospawneggs/\n\n" +
			"Key for configuration values:\n\n" +
			"allowAllMonsterSpawns: Disable the blocking of Monsters\n" +
			"allowAllAnimalSpawns: Disable the blocking of Animals\n" +
			"allowAllNPCSpawns: Disable the blocking of NPC spawns\n" +
			"allowAllUnknownSpawns: Can mobs unknown to the plugin be spawned freely? (Not recommended)\n" +
			"blockSnowGolems: [Broken]\n" +
			"onlyBlockGolemsInCreative: [Broken]\n";
}
