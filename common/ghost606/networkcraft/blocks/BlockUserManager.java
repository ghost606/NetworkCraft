package ghost606.networkcraft.blocks;

import ghost606.networkcraft.Networkcraft;
import ghost606.networkcraft.information.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockUserManager extends Block {

	public BlockUserManager(int id) {
		super(id, Material.iron);
		this.setHardness(3.0F);
		this.setCreativeTab(Networkcraft.tabNetworkcraft);
		this.setUnlocalizedName(BlockInfo.Safe.NAME);
	}
	
	
}
