package ghost606.networkcraft.configuration;

import ghost606.networkcraft.information.BlockInfo;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	private static Configuration conf;
	
	public static void init(File file)
	{
		conf = new Configuration(file);
		
		BlockInfo.Safe.ID = conf.get(BlockInfo.Sections.BLOCKS, BlockInfo.Safe.KEY, BlockInfo.Safe.DEFAULT_ID).getInt();
		
		conf.save();
	}
}
