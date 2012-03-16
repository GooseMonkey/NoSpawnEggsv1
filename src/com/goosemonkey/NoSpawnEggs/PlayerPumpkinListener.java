package com.goosemonkey.NoSpawnEggs;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Blocks Iron and Snow Golems
 * @author GooseMonkey97
 */
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
			Location loc = e.getBlock().getLocation();
			
			// Snow Golem
			if (loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()).getType().equals(Material.SNOW_BLOCK)
				&& loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-2, loc.getBlockZ()).getType().equals(Material.SNOW_BLOCK))
			{
				if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.snowgolem.*")||
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
						if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.snowgolem.creative"))
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
						
						if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.snowgolem.survival"))
						{
							return;
						}
					}
				}
				
				e.setCancelled(true);
				e.getPlayer().sendMessage("§e" + NoSpawnEggs.getLocaleConfig().getString
						("noSnowGolemPerms", 
								"You don't have permission to create Snow Golems."));
			}
			else if ((loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()).getType().equals(Material.IRON_BLOCK)
					&& loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-2, loc.getBlockZ()).getType().equals(Material.IRON_BLOCK)) &&
				((loc.getWorld().getBlockAt(loc.getBlockX()+1, loc.getBlockY()-1, loc.getBlockZ()).getType().equals(Material.IRON_BLOCK) &&
						loc.getWorld().getBlockAt(loc.getBlockX()-1, loc.getBlockY()-1, loc.getBlockZ()).getType().equals(Material.IRON_BLOCK)) ||
						((loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()+1).getType().equals(Material.IRON_BLOCK) &&
								loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()-1).getType().equals(Material.IRON_BLOCK)))))
			{
				if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.irongolem.*")||
						!NoSpawnEggs.getMainConfig().getBoolean("ironGolemBlocking.enable", true))
				{
					return;
				}
				
				try
				{
					if (NoSpawnEggs.getMainConfig().getList("ironGolemBlocking.ignoredWorlds").contains(
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
						if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.irongolem.creative"))
						{
							return;
						}
					}
					
					if (playerGM.equals(GameMode.SURVIVAL))
					{
						if (NoSpawnEggs.getMainConfig().getBoolean("ironGolemBlocking.onlyCreative", true))
						{
							return;
						}
						
						if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.irongolem.survival"))
						{
							return;
						}
					}
				}
				
				e.setCancelled(true);
				e.getPlayer().sendMessage("§e" + NoSpawnEggs.getLocaleConfig().getString
						("noIronGolemPerms", 
								"You don't have permission to create Iron Golems."));
			}
		}
	}
}
