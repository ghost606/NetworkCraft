package ghost606.networkcraft.blocks;

import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.tileentities.TileEntitySafe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static BlockSafe safeChest;
	public static BlockUserManager userManager;
	
	public static void initBlocks()
	{
		safeChest = new BlockSafe(BlockInfo.Safe.ID);
		userManager = new BlockUserManager(BlockInfo.UserManager.ID);
		
		GameRegistry.registerBlock(safeChest, BlockInfo.Safe.KEY);
		GameRegistry.registerBlock(userManager, BlockInfo.UserManager.KEY);
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(safeChest, BlockInfo.Safe.NAME);
		LanguageRegistry.addName(userManager, BlockInfo.UserManager.NAME);
	}
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySafe.class, BlockInfo.Safe.TE_KEY);
	}
}
