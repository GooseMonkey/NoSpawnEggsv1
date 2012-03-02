package com.goosemonkey.NoSpawnEggs;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

public class DispenseListener implements Listener
{
	@EventHandler // Handle dispensed spawner eggs
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
	
	@EventHandler // Handle Fire Charge blocking
	public void onDispense2(BlockDispenseEvent event)
	{
		// Preliminaries
		try
		{
			if (event.getItem().getTypeId() != Material.FIREBALL.getId())
			{
				return;
			}
		}
		catch (NullPointerException exc)
		{
			// nothing dispensed? or just a null item
			return;
		}

		if (!NoSpawnEggs.getMainConfig().getBoolean("fireChargeDispenseBlocking.enable", true))
		{
			return;
		}
		
		try
		{
			if (NoSpawnEggs.getMainConfig().getList("fireChargeDispenseBlocking.ignoredWorlds").
					contains(event.getBlock().getWorld().getName()))
			{
				return;
			}
		}
		catch (NullPointerException exc)
		{
			// list dont exist
		}
		
		event.setCancelled(true);
	}
}
