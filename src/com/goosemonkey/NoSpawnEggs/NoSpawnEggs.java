package com.goosemonkey.NoSpawnEggs;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.goosemonkey.NoSpawnEggs.config.Config;
import com.goosemonkey.NoSpawnEggs.config.ConfigObject;
import com.goosemonkey.NoSpawnEggs.config.CustomNames;
import com.goosemonkey.NoSpawnEggs.config.Names;

public class NoSpawnEggs extends JavaPlugin {

	private PluginDescriptionFile pdf;
	private Logger log;
	private CustomNames customNames;
	
	public void onEnable() 
	{		
		//initialize vars
		pdf = this.getDescription();
		log = this.getServer().getLogger();
		customNames = new CustomNames(this);
		
		Config.setup(new ConfigObject());
		
		PlayerEggThrowListener petl = new PlayerEggThrowListener(this);
		this.getServer().getPluginManager().registerEvents(petl, this);
		
//		PlayerPumpkinListener ppl = new PlayerPumpkinListener(this);
//		this.getServer().getPluginManager().registerEvents(ppl, this);
		
		ChickenEggListener cel = new ChickenEggListener(this);
		this.getServer().getPluginManager().registerEvents(cel, this);
		
		log.info("NoSpawnEggs v"+pdf.getVersion()+" enabled!");
	}

	public void onDisable()
	{
		log.info("NoSpawnEggs v"+pdf.getVersion()+" disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof ConsoleCommandSender)
		{
			Config.setup(new ConfigObject());
			this.customNames.loadYamls();
			sender.sendMessage(Config.getName(Names.RELOADED));
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
		
		if (player.hasPermission("nospawneggs.reload") || player.isOp())
		{
			Config.setup(new ConfigObject());
			player.sendMessage(Config.getName(Names.RELOADED));
			return true;
		}
		else
		{
			player.sendMessage(Config.getName(Names.NO_RELOAD_PERM));
			return true;
		}
	}
	
	public CustomNames getCustomNames()
	{
		return this.customNames;
	}
	
	public static File getPluginDirectory()
	{
		return new File("plugins/NoSpawnEggs");
	}
}
