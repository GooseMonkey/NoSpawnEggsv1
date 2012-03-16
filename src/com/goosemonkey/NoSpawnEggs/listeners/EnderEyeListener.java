/**
 * 
 */
package com.goosemonkey.NoSpawnEggs.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.goosemonkey.NoSpawnEggs.NoSpawnEggs;

/**
 * Blocks Eyes of Ender
 * @author GooseMonkey97
 */
public class EnderEyeListener implements Listener
{
	@EventHandler
	public void onEnderEyeUse(PlayerInteractEvent event)
	{
		if (event.getItem() == null)
			return;
		
		Material item = event.getItem().getType();
		
		if (!item.equals(Material.EYE_OF_ENDER) && !item.equals(Material.ENDER_PEARL))
			return;
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) &&
				!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		
		if (item.equals(Material.EYE_OF_ENDER))
			this.handleEye(event);
		
		if (item.equals(Material.ENDER_PEARL))
			this.handlePearl(event);
	}
	
	private void handleEye(PlayerInteractEvent event)
	{
		if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.ender.eye"))
			return;
		
		if (!NoSpawnEggs.getMainConfig().getBoolean("enderBlocking.blockEnderEye", true))
			return;
		
		if (NoSpawnEggs.getMainConfig().getList("enderBlocking.enderEyeIgnoredWorlds") != null)
		{
			if (NoSpawnEggs.getMainConfig().getList("enderBlocking.enderEyeIgnoredWorlds")
					.contains(event.getPlayer().getWorld().getName()))
				return;
		}
		
		if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL) &&
				NoSpawnEggs.getMainConfig().getBoolean("enderBlocking.eyeOnlyBlockCreative", true))
			return;
		
		event.setCancelled(true);
		event.getPlayer().sendMessage(ChatColor.YELLOW + NoSpawnEggs.getLocaleConfig().getString(
				"noEnderEyePerm", "You don't have permission to throw Eyes of Ender."));
	}
	
	private void handlePearl(PlayerInteractEvent event)
	{
		if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.ender.pearl"))
			return;
		
		if (!NoSpawnEggs.getMainConfig().getBoolean("enderBlocking.blockEnderPearl", true))
			return;
		
		if (NoSpawnEggs.getMainConfig().getList("enderBlocking.enderPearlIgnoredWorlds") != null)
		{
			if (NoSpawnEggs.getMainConfig().getList("enderBlocking.enderPearlIgnoredWorlds")
					.contains(event.getPlayer().getWorld().getName()))
				return;
		}
		
		event.setCancelled(true);
		event.getPlayer().sendMessage(ChatColor.YELLOW + NoSpawnEggs.getLocaleConfig().getString(
				"noEnderPearlPerm", "You don't have permission to throw Ender Pearls."));
	}
}
