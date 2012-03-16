package com.goosemonkey.NoSpawnEggs.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;

import com.goosemonkey.NoSpawnEggs.NoSpawnEggs;

/**
 * Blocks players and dispensers from spawning chickens with eggs
 * @author GooseMonkey97
 *
 */
public class ChickenEggListener implements Listener
{
	@EventHandler
	public void onPlayerEggThrow(PlayerEggThrowEvent e)
	{
		if (!NoSpawnEggs.getMainConfig().getBoolean("chickenEggBlocking.eggThrowBlocking.enable", true))
		{
			return;
		}
		
		if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.chickenegg.*"))
		{
			return;
		}
		
		try
		{		
			if (NoSpawnEggs.getMainConfig().getList("chickenEggBlocking.eggThrowBlocking.ignoredWorlds").
					contains(e.getPlayer().getWorld().getName()))
			{
				return;
			}
		}
		catch (NullPointerException exc)
		{
			//if the list doesn't exist
			//nada
		}
		
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE)
		{
			if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.chickenegg.creative"))
			{
				return;
			}
		}
		
		if (e.getPlayer().getGameMode() == GameMode.SURVIVAL)
		{
			if (NoSpawnEggs.hasPermission(e.getPlayer(), "nospawneggs.chickenegg.survival"))
			{
				return;
			}
			
			if (NoSpawnEggs.getMainConfig().getBoolean("chickenEggBlocking.eggThrowBlocking.onlyCreative", true))
			{
				return;
			}
		}
		
		if (NoSpawnEggs.getMainConfig().getBoolean("chickenEggBlocking.eggThrowBlocking.sendMessage", true))
		{
			e.getPlayer().sendMessage("§e" + NoSpawnEggs.getLocaleConfig().getString
					("noChickenEggPerms",
							"You don't have permission to spawn Chickens from eggs."));
		}
		
		e.setHatching(false);
		e.setNumHatches((byte) 0);
	}
	
	@EventHandler
	public void onDispense(BlockDispenseEvent e)
	{
		if (e.getItem() == null || !NoSpawnEggs.getMainConfig().getBoolean(
				"chickenEggBlocking.dispenseBlocking.enable"))
		{
			return;
		}
		
		try
		{		
			if (NoSpawnEggs.getMainConfig().getList("chickenEggBlocking.dispenseBlocking.ignoredWorlds").
					contains(e.getBlock().getWorld().getName()))
			{
				return;
			}
		}
		catch (NullPointerException exc)
		{
			//if the list doesn't exist
			//nada
		}
		
		ItemStack item = e.getItem();
		
		if (item.getType() == Material.EGG)
		{
			//Turn the egg into a snow ball so it still fires/hits things but
			//doesn't spawn chickens
			e.setItem(new ItemStack(Material.SNOW_BALL));
		}
	}
}
