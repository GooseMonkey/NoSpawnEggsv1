package com.goosemonkey.NoSpawnEggs;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.goosemonkey.NoSpawnEggs.config.Config;
import com.goosemonkey.NoSpawnEggs.config.Names;

public class PlayerPumpkinListener implements Listener
{
	private NoSpawnEggs plugin;
	public PlayerPumpkinListener(NoSpawnEggs instance)
	{
		plugin = instance;
	}
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void onBlockPlace(BlockPlaceEvent e)
	{
		e.getPlayer().sendMessage("1 "+e.getBlockPlaced().getTypeId());//
		if (e.getBlockPlaced().getType().equals(Material.PUMPKIN)|| e.getBlockPlaced().getType().equals(Material.JACK_O_LANTERN))
		{
			e.getPlayer().sendMessage("2");//
			if (e.getPlayer().hasPermission("nospawneggs.snowgolem.*")||
					!plugin.getConfig().getBoolean("blockSnowGolems", true))
			{
				return;
			}
			e.getPlayer().sendMessage("3");//

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
				e.getPlayer().sendMessage("§e"+Config.getName(Names.NO_GOLEM_PERM));
			}
		}
	}
}
