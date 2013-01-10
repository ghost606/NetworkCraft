package networkcraft.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.StepSound;
import networkcraft.core.NetworkCraft;

public class MetaBlock extends Block {
	public MetaBlock(int id, int par) {
		super(id, par, Material.rock);
		this.setStepSound(Block.soundStoneFootstep);
    	this.setHardness(8.0F);
        this.setResistance(1.0F);
		this.setRequiresSelfNotify();
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	@SideOnly(Side.CLIENT)
	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		switch (meta)
		{
		case 0:
			return 0;
		case 1:
			return 1;
		default:
			return 0;
		}
	}
	@SideOnly(Side.CLIENT)
	public String getTextureFile()
	{
		return "/networkcraft/resources/blocks.png";
	}
	public int damageDropped(int meta)
	{
		return meta;
	}
	public int quantityDropped(int meta)
    {
        return meta;
    }
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(int id, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 2; i++)
        {
            list.add(new ItemStack(id, 1, i));
        }
    }
}
