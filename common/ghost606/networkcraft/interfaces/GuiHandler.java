package ghost606.networkcraft.interfaces;

import ghost606.networkcraft.Networkcraft;
import ghost606.networkcraft.interfaces.safechest.ContainerSafeChest;
import ghost606.networkcraft.interfaces.safechest.GuiSafeChest;
import ghost606.networkcraft.tileentities.TileEntitySafeChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {

	public GuiHandler()
	{
		NetworkRegistry.instance().registerGuiHandler(Networkcraft.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (te != null && te instanceof TileEntitySafeChest)
			{
				return new ContainerSafeChest(player.inventory, (TileEntitySafeChest)te);
			}
			break;

		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case 0:
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (te != null && te instanceof TileEntitySafeChest)
			{
				return new GuiSafeChest(player.inventory, (TileEntitySafeChest)te);
			}
			break;

		}
		return null;
	}
}
