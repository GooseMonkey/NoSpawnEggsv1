package com.goosemonkey.NoSpawnEggs;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Blocks throwing XP Bottles
 * @author GooseMonkey97
 */
public class XPBottleListener implements Listener
{
	@EventHandler
	public void onBottleThrow(PlayerInteractEvent event)
	{
		// Preliminaries.
		if (!NoSpawnEggs.getMainConfig().getBoolean("expBottleBlocking.enable", true))
		{
			return;
		}
		
		if (event.getPlayer().getItemInHand() == null)
		{
			return;
		}
		
		if (!event.getPlayer().getItemInHand().getType().equals(Material.EXP_BOTTLE))
		{
			return;
		}
		
		if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.expbottle.*") || event.getPlayer().isOp())
		{
			return;
		}
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			return;
		}
		
		Player player = event.getPlayer();
		
		if (player.getGameMode() == GameMode.CREATIVE)
		{
			if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.expbottle.creative"))
			{
				return;
			}
		}
		
		if (player.getGameMode() == GameMode.SURVIVAL)
		{
			if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.expbottle.survival"))
			{
				return;
			}
			
			if (NoSpawnEggs.getMainConfig().getBoolean("expBottleBlocking.onlyCreative", true))
			{
				return;
			}
		}
		
		if (NoSpawnEggs.getMainConfig().getList("expBottleBlocking.ignoredWorlds").contains(
				player.getWorld().getName()))
		{
			return;
		}
		
		event.setCancelled(true);
		
		player.sendMessage("§e" + NoSpawnEggs.getLocaleConfig().getString("noExpBottlePerm",
				"You don't have pemission to spawn Experience Orbs."));
	}
}
