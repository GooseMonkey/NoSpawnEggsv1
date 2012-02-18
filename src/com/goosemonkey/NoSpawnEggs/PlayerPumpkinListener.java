package com.goosemonkey.NoSpawnEggs;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerPumpkinListener implements Listener
{
	@EventHandler (priority = EventPriority.LOWEST)
	public void onBlockPlace(BlockPlaceEvent e)
	{
		if (e.getBlockPlaced() == null || e.getPlayer() == null)
		{
			return;
		}
		
		if (e.getBlockPlaced().getType().equals(Material.PUMPKIN)|| e.getBlockPlaced().getType().equals(Material.JACK_O_LANTERN))
		{
			if (e.getPlayer().hasPermission("nospawneggs.snowgolem.*")||
					!NoSpawnEggs.getMainConfig().getBoolean("snowGolemBlocking.enable", true))
			{
				return;
			}
			
			try
			{
				if (NoSpawnEggs.getMainConfig().getList("snowGolemBlocking.ignoredWorlds").contains(
						e.getPlayer().getWorld().getName()))
				{
					return;
				}
			}
			catch (NullPointerException exc)
			{
				//list dont exist
			}
			
			GameMode playerGM = e.getPlayer().getGameMode();
			
			if (playerGM != null)
			{
				if (playerGM.equals(GameMode.CREATIVE))
				{
					if (e.getPlayer().hasPermission("nospawneggs.snowgolem.creative"))
					{
						return;
					}
				}
				
				if (playerGM.equals(GameMode.SURVIVAL))
				{
					if (NoSpawnEggs.getMainConfig().getBoolean("snowGolemBlocking.onlyCreative", true))
					{
						return;
					}
					
					if (e.getPlayer().hasPermission("nospawneggs.snowgolem.survival"))
					{
						return;
					}
				}
			}

			Location loc = e.getBlock().getLocation();
			
			if (loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()).getType().equals(Material.SNOW_BLOCK)
				&& loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-2, loc.getBlockZ()).getType().equals(Material.SNOW_BLOCK))
			{
				e.setCancelled(true);
				e.getPlayer().sendMessage("§e" + NoSpawnEggs.getLocaleConfig().getString
						("noSnowGolemPerms", 
								"You don't have permission to create Snow Golems."));
			}
		}
	}
}
