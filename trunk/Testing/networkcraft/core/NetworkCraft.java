package networkcraft.core; 	

import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import networkcraft.blocks.MetaBlock;
import networkcraft.blocks.MetaBlockStorage;
import networkcraft.items.metaIngot;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "networkcraft", name = "NetworkCraft", version = "1.0.0 Pre Alpha")
@NetworkMod(clientSideRequired = true  , serverSideRequired = false)

public class NetworkCraft {
	public final int oreBlockId = 230; //OreId
	public final int metaIngotId = 550; //IngotId
	public static Block metaBlock;
	public static Item metaItem;
	
	@SidedProxy(clientSide = "networkcraft.core.ClientProxy", serverSide= "networkcraft.core.CommonProxy")
    public static CommonProxy proxy;
	
	@Init
	public void Load(FMLInitializationEvent event)
	{
		proxy.registerRenderInformation();

		//Adding blocks
		metaBlock = new MetaBlock(oreBlockId, 0).setBlockName("metaBlock");
		
		GameRegistry.registerBlock(metaBlock, MetaBlockStorage.class);
		
		LanguageRegistry.addName(new ItemStack(metaBlock, 1, 0), "Titanium Ore");
		LanguageRegistry.addName(new ItemStack(metaBlock, 1, 1), "Copper Ore");
		
		//Adding items
		metaItem = new metaIngot(metaIngotId).setItemName("metaItem");
		
		LanguageRegistry.addName(new ItemStack(metaItem, 1, 0), "Titanium Ingot");
		LanguageRegistry.addName(new ItemStack(metaItem, 1, 1), "Copper Ingot");
		
	}

}

