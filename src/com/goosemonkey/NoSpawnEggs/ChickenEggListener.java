package com.goosemonkey.NoSpawnEggs;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;

import com.goosemonkey.NoSpawnEggs.config.Config;
import com.goosemonkey.NoSpawnEggs.config.Names;
import com.goosemonkey.NoSpawnEggs.config.Property;

public class ChickenEggListener implements Listener
{
	private NoSpawnEggs plugin;
	
	public ChickenEggListener(NoSpawnEggs inst)
	{
		plugin = inst;
	}
	
	@EventHandler
	public void onPlayerEggThrow(PlayerEggThrowEvent e)
	{
		if (!Config.getBoolean(Property.ENABLE_CHICKEN_EGG_BLOCKING) ||
				e.getPlayer().hasPermission("nospawneggs.chickenegg.*"))
		{
			return;
		}
		
		GameMode gm = e.getPlayer().getGameMode();
		
		if (gm != null)
		{
			if (gm == GameMode.CREATIVE &&
					e.getPlayer().hasPermission("nospawneggs.chickenegg.creative"))
			{
				return;
			}
			
			if (gm == GameMode.SURVIVAL &&
					e.getPlayer().hasPermission("nospawneggs.chickenegg.survival"))
			{
				return;
			}
		}
		
		if (Config.getBoolean(Property.CHICKEN_EGG_MESSAGE))
		{
			e.getPlayer().sendMessage("§e"+Config.getName(Names.NO_CHICKEN_EGG_PERM));
		}
		
		e.setHatching(false);
		e.setNumHatches((byte) 0);
	}
	
	@EventHandler
	public void onDispense(BlockDispenseEvent e)
	{
		if (e.getItem() == null || !Config.getBoolean(Property.BLOCK_DISPENSER_CHICKEN_SPAWN))
		{
			return;
		}
		
		ItemStack item = e.getItem();
		
		if (item.getType() == Material.EGG)
		{
			e.setItem(new ItemStack(Material.SNOW_BALL));
		}
	}
	
	public NoSpawnEggs getPlugin()
	{
		return this.plugin;
	}
}
