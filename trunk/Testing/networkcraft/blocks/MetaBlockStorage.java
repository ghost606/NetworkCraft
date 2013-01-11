package networkcraft.blocks;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import networkcraft.core.NetworkCraft;

public class MetaBlockStorage extends ItemBlock {

	public MetaBlockStorage(int id, Block block) {
		super(id);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	public String getItemNameIS(ItemStack itemstack) 
	{
		String name = "";
		switch(itemstack.getItemDamage()) 
		{
		case 0:
			name = "titaniumOre";
			break;
		case 1: 
			name = "copperOre"; 
			break;
	   	default:
	   		name = "titaniumOre";
		}
		return getItemName() + "." + name;
	}
	public int getMetadata(int meta)
	{
		return meta;
	}
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int par1)
	{
		return NetworkCraft.metaBlock.getBlockTextureFromSideAndMetadata(2, par1);
	}
}
