package com.goosemonkey.NoSpawnEggs.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.goosemonkey.NoSpawnEggs.NoSpawnEggs;

public class CustomNames
{
	private NoSpawnEggs plugin;
	
	private File ymlFile;
	private FileConfiguration yml;
	
	public CustomNames(NoSpawnEggs inst)
	{
		plugin = inst;
		
		this.ymlFile = new File(plugin.getDataFolder(), "customNames.yml");
		
		if (!ymlFile.exists())
		{
			copy(plugin.getResource("customNames.yml"), ymlFile);
		}
		
		this.yml = new YamlConfiguration();

		this.yml.options().header(header);
		
		this.loadYamls();
		this.saveYamls();
	}
	
	private void copy(InputStream in, File file)
	{
        try
        {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            
            while((len=in.read(buf))>0)
            {
                out.write(buf,0,len);
            }
            
            out.close();
            in.close();
        }
        catch (Exception e)
        {
            //nada
        }
    }
	
	public void loadYamls()
	{
        try
        {
            yml.load(ymlFile);
        }
        catch (Exception e)
        {
            //nada
        }
    }
	
	public void saveYamls()
	{
        try
        {
            yml.save(ymlFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
	
	public String header = 
			"Define custom mob names and messages here.\n" +
			"Find the mob's entity ID, and add it like the one shown.\n" +
			"Vanilla mobs are to be specified in \"names.yml\", they do nothing here.\n"
			;
	
	public FileConfiguration getCustomNames()
	{
		return yml;
	}
}
