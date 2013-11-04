package ghost606.networkcraft.block;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ghost606.networkcraft.Networkcraft;
import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.information.ModInfo;
import ghost606.networkcraft.tileentities.TileEntityRightManager;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockUserManager extends BlockContainer {

	private static Icon blockIcon;	
	
	public BlockUserManager(int id) {
		super(id, Material.iron);
		this.setHardness(3.0F);
		this.setCreativeTab(Networkcraft.tabNetworkcraft);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityRightManager();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, Networkcraft.instance, 2, world, x, y, z);
		}
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		blockIcon = icon.registerIcon(ModInfo.Textures.ROOTFOLDER + ":" + BlockInfo.UserManager.ICON_NAME);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metaData) {
		return blockIcon;
	}
}
