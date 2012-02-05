package com.goosemonkey.NoSpawnEggs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.goosemonkey.NoSpawnEggs.config.Config;
import com.goosemonkey.NoSpawnEggs.config.Names;
import com.goosemonkey.NoSpawnEggs.config.Property;

public class PlayerEggThrowListener implements Listener
{
	private NoSpawnEggs plugin;
	
	public PlayerEggThrowListener(NoSpawnEggs instance)
	{
		plugin = instance;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK &&
				e.getAction() != Action.RIGHT_CLICK_AIR)
		{
			return;
		}
		
		if (e.getPlayer().getItemInHand().getTypeId() == 383)
		{
			if (e.getPlayer().hasPermission("nospawneggs.*"))
			{
				//Do nothing
				return;
			}
			
			int eggMeta = e.getPlayer().getItemInHand().getDurability();
			
			EntityType type = null;
			
			switch (eggMeta)
			{
			case 50: type = EntityType.CREEPER; break;
			case 51: type = EntityType.SKELETON; break;
			case 52: type = EntityType.SPIDER; break;
			case 54: type = EntityType.ZOMBIE; break;
			case 55: type = EntityType.SLIME; break;
			case 56: type = EntityType.GHAST; break;
			case 57: type = EntityType.PIGZOMBIE; break;
			case 58: type = EntityType.ENDERMAN; break;
			case 59: type = EntityType.CAVESPIDER; break;
			case 60: type = EntityType.SILVERFISH; break;
			case 61: type = EntityType.BLAZE; break;
			case 62: type = EntityType.MAGMACUBE; break;
			case 90: type = EntityType.PIG; break;
			case 91: type = EntityType.SHEEP; break;
			case 92: type = EntityType.COW; break;
			case 93: type = EntityType.CHICKEN; break;
			case 94: type = EntityType.SQUID; break;
			case 95: type = EntityType.WOLF; break;
			case 96: type = EntityType.MOOSHROOM; break;
			case 120: type = EntityType.VILLAGER; break;
			
			default: type = EntityType.MISSINGNO;
			}
			
			String properName = "Entity";
			
			try
			{
				properName = Config.getName(Names.valueOf("EID"+eggMeta));
			}
			catch (IllegalArgumentException ex)
			{
				//Nothing
			}
			
			if (type == EntityType.CREEPER || type == EntityType.SKELETON ||
				type == EntityType.SPIDER || type == EntityType.ZOMBIE ||
				type == EntityType.SLIME || type == EntityType.GHAST ||
				type == EntityType.PIGZOMBIE || type == EntityType.ENDERMAN ||
				type == EntityType.CAVESPIDER || type == EntityType.SILVERFISH ||
				type == EntityType.BLAZE || type == EntityType.MAGMACUBE)			
			{
				if (Config.getBoolean(Property.ALLOW_ALL_MONSTER_SPAWNS) ||
						e.getPlayer().hasPermission("nospawneggs.monster."+type))
				{
					return;
				}
				else
				{
					e.setCancelled(true);
					e.getPlayer().sendMessage(String.format("§e"+Config.getName(Names.NO_EGG_PERM), "§3"+properName+"§e"));
					return;
				}
			}
			
			if (type == EntityType.PIG || type == EntityType.SHEEP ||
				type == EntityType.COW || type == EntityType.CHICKEN ||
				type == EntityType.SQUID || type == EntityType.WOLF ||
				type == EntityType.MOOSHROOM )
			{
				if (Config.getBoolean(Property.ALLOW_ALL_ANIMAL_SPAWNS) ||
						e.getPlayer().hasPermission("nospawneggs.animal."+type))
				{
					return;
				}
				else
				{
					e.setCancelled(true);
					e.getPlayer().sendMessage(String.format("§e"+Config.getName(Names.NO_EGG_PERM), "§3"+properName+"§e"));
					return;
				}
			}
			
			if (type == EntityType.VILLAGER)
			{
				if (Config.getBoolean(Property.ALLOW_ALL_NPC_SPAWNS) ||
						e.getPlayer().hasPermission("nospawneggs.npc."+type))
				{
					return;
				}
				else
				{
					e.setCancelled(true);
					e.getPlayer().sendMessage(String.format("§e"+Config.getName(Names.NO_EGG_PERM), "§3"+properName+"§e"));
					return;
				}
			}
			
			//Unknown mobs, handling by id
			if (e.getPlayer().hasPermission("nospawneggs.unknown")||
				(Config.getBoolean(Property.ALLOW_ALL_UNKNOWN_SPAWNS)))
			{
				return;
			}
			
			if (e.getPlayer().hasPermission("nospawneggs.id."+eggMeta))
			{
				return;
			}
			
			e.setCancelled(true);
			
			String unknownName;
			
			unknownName = plugin.customNames.getCustomNames().getString(String.valueOf(eggMeta), "Entity");
			
			e.getPlayer().sendMessage(String.format("§e"+Config.getName(Names.NO_EGG_PERM),
				"§3"+unknownName+"§e"));
		}
	}
}
