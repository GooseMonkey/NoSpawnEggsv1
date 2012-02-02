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
	private NoSpawnEggs plugin;
	public PlayerPumpkinListener(NoSpawnEggs instance)
	{
		plugin = instance;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e)
	{
		e.getPlayer().sendMessage("1  "+e.getBlockPlaced().getTypeId());
		if (e.getBlockPlaced().getType().equals(Material.PUMPKIN)|| e.getBlockPlaced().getType().equals(Material.JACK_O_LANTERN))
		{
			e.getPlayer().sendMessage("2");
			if ((e.getPlayer().isOp() && plugin.getConfig().getBoolean("allowOpsAllPermissions", true)) || 
					plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.snowgolem")||
					plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.*")||
					!plugin.getConfig().getBoolean("blockSnowGolems", true))
			{
				return;
			}
			e.getPlayer().sendMessage("3");
			if (e.getPlayer().getGameMode() == GameMode.SURVIVAL && plugin.getConfig().getBoolean("onlyBlockGolemsInCreative", true))
			{
				return;
			}
			e.getPlayer().sendMessage("4");

			Location loc = e.getBlock().getLocation();
			if (loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ()).getType().equals(Material.SNOW_BLOCK)
			 && loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-2, loc.getBlockZ()).getType().equals(Material.SNOW_BLOCK))
			{
				e.setCancelled(true);
				e.getPlayer().sendMessage("§eYou don't have permission to create Snow Golems.");
			}
		}
	}
}
