package com.goosemonkey.NoSpawnEggs.listeners;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.goosemonkey.NoSpawnEggs.NoSpawnEggs;

/**
 * Blocks Spawner Eggs
 * @author GooseMonkey97
 */
public class PlayerEggThrowListener implements Listener
{
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		//Check if they were actually spawning a mob with an egg
		if (event.getAction() == Action.RIGHT_CLICK_AIR ||
				event.getAction() == Action.RIGHT_CLICK_BLOCK )
		{
			if (event.getPlayer().getItemInHand().getTypeId() == 383)
			{
				this.onPlayerSpawnerEgg(event);
			}
		}
	}
	
	private void onPlayerSpawnerEgg(PlayerInteractEvent event)
	{
		//If they have all perms or are an op, return now.
		if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.*") ||
				event.getPlayer().isOp())
		{
			return;
		}
		
		PlayerSpawnerEggEvent pseEvent = new PlayerSpawnerEggEvent(event);
		
		if (!canPlayerUseEgg(pseEvent))
		{
			event.setCancelled(true);
			
			if (!pseEvent.isCancelledBecauseOfTimer())
			{
				event.getPlayer().sendMessage(String.format("§e" + NoSpawnEggs.getLocaleConfig().getString(
						"noSpawnerEggPerms",
						"You don't have permission to spawn this %s."),
						"§3" + pseEvent.getEntityBreed().getProperName() + "§e"));
			}
			else
			{
				event.getPlayer().sendMessage("§e" + NoSpawnEggs.getLocaleConfig().getString(
						"timerMessage",
						"You have to wait a while before you can do this again."));
			}
		}
	}
	
	public boolean canPlayerUseEgg(PlayerSpawnerEggEvent event)
	{
		//First, make sure that that entity's blocking is on.
		if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.*"))
		{
			return true;
		}
		
		//Then, check per-world stuff
		if (this.isAllSpawningAllowedInWorld(event.getEntityBreed().getCategory(),
				event.getEntityId(), event.getPlayer().getWorld()))
		{
			return true;
		}
		
		//Next comes checking permissions on the player for known entities.
		if (event.getEntityBreed().getCategory() != EntityCategory.UNKNOWN)
		{
			if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs." +
					event.getEntityBreed().getCategory().name().toLowerCase() + "." +
					event.getEntityBreed().getPermName()))
			{
				return true;
			}
		}
		//Then, check for unknown entities.
		else
		{
			if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.id." +
					event.getEntityId()))
			{
				return true;
			}
		}
		
		/* 
		 * NoSpawnEggs - Beginning to implement timer. It is completely nonfunctional currently,
		 * I'll come back to it.
		 *
		
		//If they don't have permission yet, check Timer to see if it's time.
		
		if (NoSpawnEggs.hasPermission(event.getPlayer(), "nospawneggs.timer.egg"))
		{
			// There hasn't been a return yet, and they can't use timer, so block.
			return false;
		}
		
		// If they haven't used a spawn egg yet this session, set that they have and end.
		if (Timer.getLastSpawnEgg(event.getPlayer()) == -1)
		{
			Timer.chickenEggUse(event.getPlayer());
			
			return true;
		}
		
		long longSinceLast = Calendar.getInstance().getTimeInMillis() -
				Timer.getLastSpawnEgg(event.getPlayer());
		
		int secsBetweenSpawns = NoSpawnEggs.getMainConfig().getInt("timer.spawnerEggs", 30);
		
		int secsSinceLast = (int) (longSinceLast / 1000);
		
		if (secsSinceLast < secsBetweenSpawns)
		{
			event.cancelledByTimer();
			
			return false;
		}
		else
		{
			Timer.chickenEggUse(event.getPlayer());
			
			return true;
		}
		
		*/
		
		// At this point, assume to block.
		return false;		
		
	}
	
	public boolean isAllSpawningAllowedInWorld(EntityCategory cat, int id, World world)
	{
		//If the category is set for per-world, use that.
		if (NoSpawnEggs.getMainConfig().isSet("allowAllSpawns.perWorld." +
				world.getName() + "." + cat.name().toLowerCase()))
		{
			if (NoSpawnEggs.getMainConfig().getBoolean("allowAllSpawns.perWorld." +
				world.getName() + "." + cat.name().toLowerCase()) == true )
			{
				//If that world allows that category, check the block list first
				if (NoSpawnEggs.getMainConfig().getList("allowAllSpawns.perWorld." +
						world.getName() + ".blockedIds").contains(id))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
			else
			{
				//If that world does NOT allow that category, check the allow list
				if (NoSpawnEggs.getMainConfig().getList("allowAllSpawns.perWorld." +
						world.getName() + ".allowedIds").contains(id))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		//If it's not set for per-world, go to global.
		{
			if (NoSpawnEggs.getMainConfig().getBoolean("allowAllSpawns." + 
					cat.name().toLowerCase()))
			{
				//If it's globally allowed, make sure it's not blocked for that world in ID first.
				try
				{
					if (NoSpawnEggs.getMainConfig().getList("allowAllSpawns.perWorld." +
							world.getName() + ".blockedIds").contains(id))
					{
						return false;
					}
					else
					{
						return true;
					}
				}
				catch (NullPointerException exc)
				{
					return true;
				}
			}
			else
			{
				//If it's NOT globally allowed, check for exceptions in allowed ID list
				try
				{
					if (NoSpawnEggs.getMainConfig().getList("allowAllSpawns.perWorld." +
							world.getName() +
							".allowedIds").contains(id))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				catch (NullPointerException exc)
				{
					return false;
				}
			}
		}
	}
	
	public class PlayerSpawnerEggEvent
	{
		private Player player = null;
		private PlayerInteractEvent event = null;
		private int eggMeta;
		private EntityType breed = null;
		private boolean timerCancelled = false;
		
		PlayerSpawnerEggEvent(PlayerInteractEvent event)
		{
			this.player = event.getPlayer();
			
			this.event = event;
			
			this.eggMeta = event.getPlayer().getItemInHand().getDurability();
			
			switch (eggMeta)
			{
			case 50: breed = EntityType.CREEPER; break;
			case 51: breed = EntityType.SKELETON; break;
			case 52: breed = EntityType.SPIDER; break;
			case 54: breed = EntityType.ZOMBIE; break;
			case 55: breed = EntityType.SLIME; break;
			case 56: breed = EntityType.GHAST; break;
			case 57: breed = EntityType.PIGZOMBIE; break;
			case 58: breed = EntityType.ENDERMAN; break;
			case 59: breed = EntityType.CAVESPIDER; break;
			case 60: breed = EntityType.SILVERFISH; break;
			case 61: breed = EntityType.BLAZE; break;
			case 62: breed = EntityType.MAGMACUBE; break;
			case 90: breed = EntityType.PIG; break;
			case 91: breed = EntityType.SHEEP; break;
			case 92: breed = EntityType.COW; break;
			case 93: breed = EntityType.CHICKEN; break;
			case 94: breed = EntityType.SQUID; break;
			case 95: breed = EntityType.WOLF; break;
			case 96: breed = EntityType.MOOSHROOM; break;
			case 98: breed = EntityType.OCELOT; break;
			case 120: breed = EntityType.VILLAGER; break;
			
			default: breed = null;
			}
		}
		
		public Player getPlayer()
		{
			return player;
		}
		
		public PlayerInteractEvent getInteractEvent()
		{
			return event;
		}
		
		public int getEntityId()
		{
			return eggMeta;
		}
		
		public EntityType getEntityBreed()
		{
			if (breed != null)
			{
				return breed;
			}
			else
			{
				return EntityType.UNKNOWN;
			}
		}
		
		public boolean isCancelledBecauseOfTimer()
		{
			return this.timerCancelled;
		}
		
		public void cancelledByTimer()
		{
			this.timerCancelled = true;
		}
	}
	
	public enum EntityCategory
	{
		ANIMAL, MONSTER, NPC, UNKNOWN
	}
	
	public enum EntityType {

		CREEPER(50, EntityCategory.MONSTER),
		SKELETON(51, EntityCategory.MONSTER),
		SPIDER(52, EntityCategory.MONSTER),
		ZOMBIE(54, EntityCategory.MONSTER),
		SLIME(55, EntityCategory.MONSTER),
		GHAST(56, EntityCategory.MONSTER),
		PIGZOMBIE(57, EntityCategory.MONSTER),
		ENDERMAN(58, EntityCategory.MONSTER),
		CAVESPIDER(59, EntityCategory.MONSTER),
		SILVERFISH(60, EntityCategory.MONSTER),
		BLAZE(61, EntityCategory.MONSTER),
		MAGMACUBE(62, EntityCategory.MONSTER),
		PIG(90, EntityCategory.ANIMAL),
		SHEEP(91, EntityCategory.ANIMAL),
		COW(92, EntityCategory.ANIMAL),
		CHICKEN(93, EntityCategory.ANIMAL),
		SQUID(94, EntityCategory.ANIMAL),
		WOLF(95, EntityCategory.ANIMAL),
		MOOSHROOM(96, EntityCategory.ANIMAL),
		OCELOT(98, EntityCategory.ANIMAL),
		VILLAGER(120, EntityCategory.NPC),
		UNKNOWN(0, EntityCategory.UNKNOWN);
		
		int Id = 0;
		EntityCategory cat = EntityCategory.UNKNOWN;
		
		EntityType(int eId, EntityCategory cat)
		{
			Id = eId;
			this.cat = cat;
		}
		
		public String getPermName()
		{
			return this.name().toLowerCase();
		}
		
		public EntityCategory getCategory()
		{
			return this.cat;
		}
		
		public int getId()
		{
			return Id;
		}
		
		public String getProperName()
		{
			return NoSpawnEggs.getLocaleConfig().getString(
					"entity.EID" + this.getId(), NoSpawnEggs.getLocaleConfig().getString(
							"entity.0", "Entity"));
		}
	}
}
