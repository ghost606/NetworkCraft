package ghost606.networkcraft.block;

import ghost606.networkcraft.information.BlockInfo;
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
		
		GameRegistry.registerBlock(safeChest, BlockInfo.Safe.NAME);
		GameRegistry.registerBlock(rightManager, BlockInfo.RightManager.NAME);
		GameRegistry.registerBlock(userManager, BlockInfo.UserManager.NAME);
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(safeChest, BlockInfo.Safe.NAME);
		LanguageRegistry.addName(rightManager, BlockInfo.RightManager.NAME);
		LanguageRegistry.addName(userManager, BlockInfo.UserManager.NAME);
	}
}
