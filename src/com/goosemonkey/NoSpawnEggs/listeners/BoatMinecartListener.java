/**
 * 
 */
package com.goosemonkey.NoSpawnEggs.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.goosemonkey.NoSpawnEggs.NoSpawnEggs;

/**
 * @author GooseMonkey97
 */
public class BoatMinecartListener implements Listener
{
	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		if (event.getItem() == null)
			return;
		
		Material item = event.getItem().getType();
		
		if (!item.equals(Material.BOAT) && !item.equals(Material.MINECART))
			return;
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) &&
				!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		
		if (item.equals(Material.BOAT))
			this.handleBoat(event);
		
		if (item.equals(Material.MINECART))
			this.handleMinecart(event);
	}
	
	private void handleBoat(PlayerInteractEvent event)
	{
		if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.boat.*"))
			return;
		
		if (!NoSpawnEggs.getMainConfig().getBoolean("boatBlocking.enable", true))
			return;
		
		if (NoSpawnEggs.getMainConfig().getList("boatBlocking.ignoredWorlds") != null)
		{
			if (NoSpawnEggs.getMainConfig().getList("boatBlocking.ignoredWorlds")
					.contains(event.getPlayer().getWorld().getName()))
				return;
		}
		
		if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL) &&
				NoSpawnEggs.getMainConfig().getBoolean("boatBlocking.onlyCreative", true))
			return;
		
		Player ply = event.getPlayer();
		
		if (ply.getGameMode().equals(GameMode.SURVIVAL))
			if (NoSpawnEggs.hasPermission(ply, "nospawneggs.boat.survival"))
				return;
		
		if (ply.getGameMode().equals(GameMode.CREATIVE))
			if (NoSpawnEggs.hasPermission(ply, "nospawneggs.boat.creative"))
				return;
		
		event.setCancelled(true);
		event.getPlayer().sendMessage(ChatColor.YELLOW + NoSpawnEggs.getLocaleConfig().getString(
				"noBoatPerm", "You don't have permission to create boats."));
	}
	
	private void handleMinecart(PlayerInteractEvent event)
	{
		if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.minecart.*"))
			return;
		
		if (!NoSpawnEggs.getMainConfig().getBoolean("minecartBlocking.enable", true))
			return;
		
		if (NoSpawnEggs.getMainConfig().getList("minecartBlocking.ignoredWorlds") != null)
		{
			if (NoSpawnEggs.getMainConfig().getList("minecartBlocking.ignoredWorlds")
					.contains(event.getPlayer().getWorld().getName()))
				return;
		}
		
		if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL) &&
				NoSpawnEggs.getMainConfig().getBoolean("minecartBlocking.onlyCreative", true))
			return;
		
		Player ply = event.getPlayer();
		
		if (ply.getGameMode().equals(GameMode.SURVIVAL))
			if (NoSpawnEggs.hasPermission(ply, "nospawneggs.minecart.survival"))
				return;
		
		if (ply.getGameMode().equals(GameMode.CREATIVE))
			if (NoSpawnEggs.hasPermission(ply, "nospawneggs.minecart.creative"))
				return;
		
		event.setCancelled(true);
		event.getPlayer().sendMessage(ChatColor.YELLOW + NoSpawnEggs.getLocaleConfig().getString(
				"noMinecartPerm", "You don't have permission to create minecarts."));
	}
}
