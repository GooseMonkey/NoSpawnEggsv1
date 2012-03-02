package com.goosemonkey.NoSpawnEggs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

public class DispenseListener implements Listener
{
	@EventHandler
	public void onDispense(BlockDispenseEvent event)
	{
		// If it wasn't a spawn egg, stop.
		try
		{
			if (event.getItem().getTypeId() != 383)
			{
				return;
			}
		}
		catch (NullPointerException exc)
		{
			// nothing dispensed? or just a null item
			return;
		}
		
		// If it's disabled, stop.
		if (!NoSpawnEggs.getMainConfig().getBoolean("spawnEggDispenseBlocking.enable", true))
		{
			return;
		}
		
		// Check if it's disabled in the world
		try
		{
			if (NoSpawnEggs.getMainConfig().getList("spawnEggDispenseBlocking.ignoredWorlds").
					contains(event.getBlock().getWorld().getName()))
			{
				return;
			}
		}
		catch (NullPointerException exc)
		{
			// list dont exist
		}
		
		// Block it if it hasn't returned
		event.setCancelled(true);
	}
}
