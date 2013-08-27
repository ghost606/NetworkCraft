package ghost606.networkcraft.blocks;

import ghost606.networkcraft.entities.tileentities.TileEntitySafe;
import ghost606.networkcraft.information.BlockInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static BlockSafe safeChest;
	
	public static void initBlocks()
	{
		safeChest = new BlockSafe(BlockInfo.SafeChest.ID);
		GameRegistry.registerBlock(safeChest, BlockInfo.SafeChest.KEY);
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(safeChest, BlockInfo.SafeChest.NAME);
	}
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySafe.class, BlockInfo.SafeChest.TE_KEY);
	}
}
