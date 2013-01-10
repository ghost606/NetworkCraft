package networkcraft.items;

import java.util.List;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class metaIngot extends Item {
	public metaIngot(int par1) {
		super(par1);
		maxStackSize = 64;
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	public String getTextureFile()
    {
		return "/networkcraft/resources/items.png";
    }
	@Override
	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int meta) {
		switch(meta)
		{
		case 0:
			return 0;
		case 1:
			return 1;
		default:
			return 0;
		}
	}
	@Override
	public String getItemNameIS(ItemStack is) {
		switch(is.getItemDamage())
		{
			case 0:
				return "titaniumBar";
			case 1:
				return "copperBar";
			default:
				return "titaniumBar";
		}
		
	}
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tab, List itemList) //Adds the metadata items to the creative inventory
	{
		for(int i=0;i<2;i++){
			itemList.add(new ItemStack(itemID,1,i));
		}
	}
}
