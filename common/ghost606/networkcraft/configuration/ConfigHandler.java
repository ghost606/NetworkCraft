package ghost606.networkcraft.configuration;

import ghost606.networkcraft.information.BlockInfo;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigHandler {
	private static Configuration conf;
	
	public static void init(File file)
	{
		conf = new Configuration(file);
		
		BlockInfo.SafeChest.ID = conf.get(BlockInfo.Sections.BLOCKS, BlockInfo.SafeChest.KEY, BlockInfo.SafeChest.DEFAULT_ID).getInt();
		
		conf.save();
	}
}
