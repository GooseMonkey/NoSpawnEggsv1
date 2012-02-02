package com.goosemonkey.NoSpawnEggs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomNames
{
	private NoSpawnEggs plugin;
	
	private File ymlFile;
	protected FileConfiguration yml;
	
	public CustomNames(NoSpawnEggs inst)
	{
		plugin = inst;
		
		this.ymlFile = new File(plugin.getDataFolder(), "names.yml");
		this.yml = new YamlConfiguration();
		
		if (!ymlFile.exists())
		{
			copy(plugin.getResource("names.yml"), ymlFile);
			plugin.log.info("Generating new names.yml file.");
		}
		
		this.loadYamls();
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
            e.printStackTrace();
        }
    }
	
	public void loadYamls() {
        try
        {
            yml.load(ymlFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
}
