package ghost606.networkcraft.blocks;

import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.tileentities.TileEntitySafe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
	public static BlockSafe safeChest;
	
	public static void initBlocks()
	{
		safeChest = new BlockSafe(BlockInfo.Safe.ID);
		GameRegistry.registerBlock(safeChest, BlockInfo.Safe.KEY);
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(safeChest, BlockInfo.Safe.NAME);
	}
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySafe.class, BlockInfo.Safe.TE_KEY);
	}
}
