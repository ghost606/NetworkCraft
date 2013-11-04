package ghost606.networkcraft.block;

import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.tileentities.TileEntityRightManager;
import ghost606.networkcraft.tileentities.TileEntitySafe;
import ghost606.networkcraft.tileentities.TileEntityUserManager;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static BlockSafe safeChest;
	public static BlockRightManager rightManager;
	public static BlockUserManager userManager;
	
	public static void initBlocks()
	{
		safeChest = new BlockSafe(BlockInfo.Safe.ID);
		rightManager = new BlockRightManager(BlockInfo.RightManager.ID);
		userManager = new BlockUserManager(BlockInfo.UserManager.ID);
		
		GameRegistry.registerBlock(safeChest, BlockInfo.Safe.KEY);
		GameRegistry.registerBlock(rightManager, BlockInfo.RightManager.KEY);
		GameRegistry.registerBlock(userManager, BlockInfo.UserManager.KEY);
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(safeChest, BlockInfo.Safe.NAME);
		LanguageRegistry.addName(rightManager, BlockInfo.RightManager.NAME);
		LanguageRegistry.addName(userManager, BlockInfo.UserManager.NAME);
	}
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySafe.class, BlockInfo.Safe.TE_KEY);
		GameRegistry.registerTileEntity(TileEntityRightManager.class, BlockInfo.RightManager.TE_KEY);
		GameRegistry.registerTileEntity(TileEntityUserManager.class, BlockInfo.UserManager.TE_KEY);
	}
}
