package com.goosemonkey.NoSpawnEggs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

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
		if (e.getPlayer().getItemInHand().getTypeId() == 383)
		{
			if (plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.*")||
			   (e.getPlayer().isOp() && plugin.getConfig().getBoolean("allowOpsAllPermissions", true)))
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
			
			FileConfiguration names = plugin.names.yml;
			
			if (type != null)
			{
				properName = type.toString();
			}
			
			if (names.isSet(""+eggMeta))
			{
				properName = names.getString(""+eggMeta);
			}
			
			if (type == EntityType.CREEPER || type == EntityType.SKELETON ||
				type == EntityType.SPIDER || type == EntityType.ZOMBIE ||
				type == EntityType.SLIME || type == EntityType.GHAST ||
				type == EntityType.PIGZOMBIE || type == EntityType.ENDERMAN ||
				type == EntityType.CAVESPIDER || type == EntityType.SILVERFISH ||
				type == EntityType.BLAZE || type == EntityType.MAGMACUBE)			
			{
				if (plugin.getConfig().getBoolean("allowAllMonsterSpawns", false) ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.monster."+type) ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.monster.*") ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.*"))
				{
					return;
				}
				else
				{
					e.setCancelled(true);
					e.getPlayer().sendMessage("§eYou don't have permission to spawn this "+properName+"!");
					return;
				}
			}
			
			if (type == EntityType.PIG || type == EntityType.SHEEP ||
				type == EntityType.COW || type == EntityType.CHICKEN ||
				type == EntityType.SQUID || type == EntityType.WOLF ||
				type == EntityType.MOOSHROOM )
			{
				if (plugin.getConfig().getBoolean("allowAllAnimalSpawns", false) ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.animal."+type) ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.animal.*") ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.*"))
				{
					return;
				}
				else
				{
					e.setCancelled(true);
					e.getPlayer().sendMessage("§eYou don't have permission to spawn this "+properName+"!");
					return;
				}
			}
			
			if (type == EntityType.VILLAGER)
			{
				if (plugin.getConfig().getBoolean("allowAllNPCSpawns", false) ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.npc."+type) ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.npc.*") ||
						plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.*"))
				{
					return;
				}
				else
				{
					e.setCancelled(true);
					e.getPlayer().sendMessage("§eYou don't have permission to spawn this "+properName+"!");
					return;
				}
			}
			
			//Unknown mobs, handling by id
			if (plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.unknown")||
				plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.*")||
				plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.id."+eggMeta)||
				plugin.getPermHandler().hasPermission(e.getPlayer(), "nospawneggs.id.*")||
				plugin.getConfig().getBoolean("allowAllUnknownSpawns", false))
				{
					return;
				}
			
			e.setCancelled(true);
			e.getPlayer().sendMessage("§eYou don't have permission to spawn this "+
			(names.isString(""+eggMeta) ? names.getString(""+eggMeta) : "Entity")
			+"!");
			return;
		}
	}
}
