package com.goosemonkey.NoSpawnEggs;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

@Deprecated
public class NSEPerms
{
	private NoSpawnEggs plugin;
	
	private PermissionPlugin permType;
	
	public NSEPerms(NoSpawnEggs instance)
	{
		plugin = instance;
		
		if (plugin.getServer().getPluginManager().isPluginEnabled("PermissionsBukkit"))
		{
			permType = PermissionPlugin.PERMISSIONSBUKKIT;
			plugin.getLogger().info("Using PermissionsBukkit.");
			
			return;
		}
		
		if (plugin.getServer().getPluginManager().isPluginEnabled("PermissionsEx"))
		{
			permType = PermissionPlugin.PERMISSIONSEX;
			plugin.getLogger().info("Using PermissionsEx.");
			
			return;
		}

		plugin.getLogger().warning("No compatible Permissions system found! Defaulting to config file's settings.");
	}
	
	public boolean hasPermission(Player player, String perm)
	{
		if (this.permType == null)
		{
			return false;
		}
		
		if (this.permType.equals(PermissionPlugin.PERMISSIONSBUKKIT))
		{
			return this.handlePBukkit(player, perm);
		}
		
		if (this.permType.equals(PermissionPlugin.PERMISSIONSEX))
		{
			return this.handlePEx(player, perm);
		}
		
		return false;
	}
	
	private boolean handlePBukkit(Player player, String perm)
	{		
		return player.hasPermission(perm);
	}
	
	private boolean handlePEx(Player player, String perm)
	{
		PermissionManager manager = PermissionsEx.getPermissionManager();
		
		return (manager.has(player, perm));
	}
	
	private enum PermissionPlugin
	{
		PERMISSIONSBUKKIT, PERMISSIONSEX
	}
}
