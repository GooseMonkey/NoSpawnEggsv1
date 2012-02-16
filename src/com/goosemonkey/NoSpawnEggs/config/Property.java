package com.goosemonkey.NoSpawnEggs.config;

public enum Property
{
	ALLOW_ALL_MONSTER_SPAWNS(false, "Allow anyone (even without the permission node) to spawn all monsters"),
	ALLOW_ALL_ANIMAL_SPAWNS(false, "Allow anyone (even without the permission node) to spawn all animals"),
	ALLOW_ALL_NPC_SPAWNS(false, "Allow anyone (even without the permission node) to spawn all NPCs"),
	ALLOW_ALL_UNKNOWN_SPAWNS(false, "Allow anyone to spawn mobs unknown to the plugin (Not recommended)"),
	
	BLOCK_SNOW_GOLEMS(false, "Enable the blocking of Snow Golem builds? REQUIRES CB DEV BUILD V1923 OR LATER!!"),
	GOLEM_BLOCK_ONLY_CREATIVE(true, "Should Snow Golems only be blocked for users on Creative Mode?"),

	ENABLE_CHICKEN_EGG_BLOCKING(true, "Whether users without the permission can use chicken eggs to spawn chickens"),
	CHICKEN_EGG_MESSAGE(true, "Should people using chicken eggs be sent a message that says they cant"),
	BLOCK_DISPENSER_CHICKEN_SPAWN(true, "Prevents chickens from being spawned by dispensers");
	
	private final Object value;
	private final String comment;
	
	private Property(Object value, String comment)
	{
        this.value = value;
        this.comment = comment;
    }
	
	public Object getValue()
	{
        return (value instanceof String ? "\"" + value + '\"' : value);
    }

    public String getComment()
    {
        return comment;
    }

    public String toString()
    {
        return name();
    }
}
