package com.goosemonkey.NoSpawnEggs.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

import com.goosemonkey.NoSpawnEggs.NoSpawnEggs;

/**
 * Blocks dispensing of Spawn Eggs, Fire Charges and XP Bottles
 * @author GooseMonkey97
 */
public class DispenseListener implements Listener
{
	@EventHandler
	public void onBlockDispense(BlockDispenseEvent event)
	{
		int id = event.getItem().getTypeId();
		
		if (id == 383)
		{
			this.onSpawnEggDispense(event);
			return;
		}
		
		if (id == Material.FIREBALL.getId())
		{
			this.onFireChargeDispense(event);
			return;
		}
		
		if (id == Material.EXP_BOTTLE.getId())
		{
			this.onExpBottleDispense(event);
		}
	}
	
	public void onSpawnEggDispense(BlockDispenseEvent event)
	{
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
	
	public void onFireChargeDispense(BlockDispenseEvent event)
	{
		// Preliminaries
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
	
	public void onExpBottleDispense(BlockDispenseEvent event)
	{
		// Preliminaries
		if (!NoSpawnEggs.getMainConfig().getBoolean("expBottleBlocking.dispenseBlocking.enable", true))
		{
			return;
		}
		
		try
		{
			if (NoSpawnEggs.getMainConfig().getList("expBottleBlocking.dispenseBlocking.ignoredWorlds").
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
