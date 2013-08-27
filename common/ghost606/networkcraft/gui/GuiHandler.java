package ghost606.networkcraft.gui;

import ghost606.networkcraft.Networkcraft;
import ghost606.networkcraft.container.ContainerSafe;
import ghost606.networkcraft.entities.tileentities.TileEntitySafe;
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
			if (te != null && te instanceof TileEntitySafe)
			{
				return new ContainerSafe(player.inventory, (TileEntitySafe)te);
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
			if (te != null && te instanceof TileEntitySafe)
			{
				return new GuiSafe(player.inventory, (TileEntitySafe)te);
			}
			break;

		}
		return null;
	}
}
