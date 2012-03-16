package com.goosemonkey.NoSpawnEggs;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.goosemonkey.NoSpawnEggs.NewConfig.*;
import com.goosemonkey.NoSpawnEggs.listeners.*;

public class NoSpawnEggs extends JavaPlugin
{
	//Config files:
	private static Main mainConfig;
	private static Locale localeConfig;
	
    private static Permission perms = null;
    private static boolean vault = false;
	
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
		this.getServer().getPluginManager().registerEvents(new XPBottleListener(), this);
		this.getServer().getPluginManager().registerEvents(new EnderEyeListener(), this);
		this.getServer().getPluginManager().registerEvents(new BoatMinecartListener(), this);
		
		//Permissions
		if (this.setupPermissions())
		{
			this.getLogger().info("Hooked with Vault successfully!");
			
			NoSpawnEggs.vault = true;
		}
		
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
			if (hasPermission(sender, "nospawneggs.reload") || sender.isOp() || sender instanceof ConsoleCommandSender){
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
	
	/**
	 * Get the plugin's main configuration
	 * @return FileConfiguration for MainConfig.yml
	 */
	public static FileConfiguration getMainConfig()
	{
		return mainConfig.getConfig();
	}
	
	/**
	 * Get the plugin's locale configuration
	 * @return FileConfiguration for Locale.yml
	 */
	public static FileConfiguration getLocaleConfig()
	{
		return localeConfig.getConfig();
	}
	
	/**
	 * Find if a player has a permission using the plugin's current system
	 * @param player Player
	 * @param perm Node
	 * @return Whether they have it
	 */
	public static boolean hasPermission(Player player, String perm)
	{
		if (NoSpawnEggs.vault)
		{
			return NoSpawnEggs.perms.has(player, perm);
		}
		else
		{
			return player.hasPermission(perm);
		}
	}
	
	/**
	 * Find if a CommandSender has a permission using the plugin's current system
	 * @param sender Sender
	 * @param perm Node
	 * @return Whether they have it
	 */
	public static boolean hasPermission(CommandSender sender, String perm)
	{
		if (NoSpawnEggs.vault)
		{
			return NoSpawnEggs.perms.has(sender, perm);
		}
		else
		{
			return sender.hasPermission(perm);
		}
	}
	
	private boolean setupPermissions()
	{
		try
		{
			RegisteredServiceProvider<Permission> rsp = 
					getServer().getServicesManager().getRegistration(Permission.class);
			
			NoSpawnEggs.perms = rsp.getProvider();
		}
		catch (NoClassDefFoundError ex)
		{
			return false;
		}
		
		return perms != null;
    }
}
