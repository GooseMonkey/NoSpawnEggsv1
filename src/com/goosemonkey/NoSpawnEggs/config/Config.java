package com.goosemonkey.NoSpawnEggs.config;

public class Config
{
	public static ConfigObject config;
	
	public static void setup(ConfigObject cfg)
	{
		config = cfg;
	}
	
	public static boolean getBoolean(Property value)
	{
		return (Boolean) getValue(value.name());
	}
	
	public static int getInteger(Property value)
	{
		return (Integer.parseInt((String) getValue(value.name().toString())));
	}
	
	public static String getString(Property value)
	{
        return (String) getValue(value.name());
    }
	
	public static String getName(Names value)
	{
		return (String) config.getName(value.name());
	}
	
	public static Object getValue(String node)
	{
		return config.getProperty(node);
	}
}
