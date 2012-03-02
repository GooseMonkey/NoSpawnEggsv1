package com.goosemonkey.NoSpawnEggs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.goosemonkey.NoSpawnEggs.NewConfig.*;

public class NoSpawnEggs extends JavaPlugin
{
	//Config files:
	private static Main mainConfig;
	private static Locale localeConfig;
	
	public void onEnable() 
	{		
		//Prepare YMLs
		mainConfig = new Main(this);
		localeConfig = new Locale(this);
		
		//Regiseter Listeners
		this.getServer().getPluginManager().registerEvents(new PlayerEggThrowListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerPumpkinListener(), this);
		this.getServer().getPluginManager().registerEvents(new ChickenEggListener(), this);
		this.getServer().getPluginManager().registerEvents(new DispenseListener(), this);
		
		//Ready!
		this.getLogger().info("NoSpawnEggs v"+this.getDescription().getVersion()+" enabled!");
	}

	public void onDisable()
	{
		this.getLogger().info("NoSpawnEggs v"+this.getDescription().getVersion()+" disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (args.length != 1)
		{
			sender.sendMessage("§eNoSpawnEggs version " + this.getDescription().getVersion());
			
			return true;
		}
		
		String arg = args[0];
		
		if (arg == null)
		{
			return false;
		}
		
		if (arg.equalsIgnoreCase("reload"))
		{
			if (sender.hasPermission("nospawneggs.reload") || sender.isOp() || sender instanceof ConsoleCommandSender){
				localeConfig.reload();
				mainConfig.reload();
				
				sender.sendMessage("§e" + NoSpawnEggs.getLocaleConfig().getString("reloadedMessage", 
						"§eNoSpawnEggs config reloaded."));
			}
			else
			{
				sender.sendMessage("§e" + NoSpawnEggs.getLocaleConfig().getString("noReloadPerms", 
						"§eNoSpawnEggs config reloaded."));
			}
			
			return true;
		}
		
		return false;
	}
	
	public static FileConfiguration getMainConfig()
	{
		return mainConfig.getConfig();
	}
	
	public static FileConfiguration getLocaleConfig()
	{
		return localeConfig.getConfig();
	}
}
