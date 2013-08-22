package ghost606.networkcraft.blocks;

import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.tileentities.TileEntitySafeChest;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static BlockSafeChest safeChest;
	
	public static void initBlocks()
	{
		safeChest = new BlockSafeChest(BlockInfo.SafeChest.ID);
		GameRegistry.registerBlock(safeChest, BlockInfo.SafeChest.KEY);
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(safeChest, BlockInfo.SafeChest.NAME);
	}
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySafeChest.class, BlockInfo.SafeChest.TE_KEY);
	}
}
