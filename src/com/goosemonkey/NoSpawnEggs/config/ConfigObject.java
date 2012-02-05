package com.goosemonkey.NoSpawnEggs.config;

import java.io.File;
import java.io.FileWriter;

import org.bukkit.util.config.Configuration;

import com.goosemonkey.NoSpawnEggs.NoSpawnEggs;

@SuppressWarnings ("deprecation")
public class ConfigObject
{	
	private final File configFile = new File(NoSpawnEggs.folder, "config.yml");
	private final File namesFile = new File(NoSpawnEggs.folder, "names.yml");
	private final Configuration config = new Configuration(configFile);
    private final Configuration names = new Configuration(namesFile);
    
    public ConfigObject()
    {
    	if (!NoSpawnEggs.folder.exists()) NoSpawnEggs.folder.mkdir();
    	
    	reloadConfig();
        config.load();

        reloadNames();
        names.load();
    }
    
    private void reloadConfig()
    {
        config.load();
        
        for (Property def : Property.values())
        {
            if (config.getProperty(def.name()) == null)
            {
                writeToFile('\n' + def.name() + ": " + def.getValue() + "\n#" + def.getComment(), configFile);
            }
        }
    }
    
    private void reloadNames()
    {
        names.load();
        
        for (Names def : Names.values())
        {
            if (names.getProperty(def.name()) == null)
            {
                writeToFile('\n' + def.name() + ": \"" + def.toString() + '\"', namesFile);
            }
        }
    }
    
    private static void writeToFile(String string, File file)
    {
        try
        {
            FileWriter fw = new FileWriter(file, true);
            fw.write(string);
            fw.close();
        }
        catch (Exception e)
        {
            System.out.println("Couldn't write to file - " + file.getName());
        }
    }
    
    public Object getName(String property)
    {
        return names.getProperty(property);
    }

    public Object getProperty(String property)
    {
        return config.getProperty(property);
    }
}
