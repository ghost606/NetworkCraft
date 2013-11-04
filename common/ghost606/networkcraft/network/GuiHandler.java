package ghost606.networkcraft.network;

import ghost606.networkcraft.Networkcraft;
import ghost606.networkcraft.gui.GuiRightManager;
import ghost606.networkcraft.gui.GuiSafe;
import ghost606.networkcraft.gui.GuiUserManager;
import ghost606.networkcraft.inventory.ContainerRightManager;
import ghost606.networkcraft.inventory.ContainerSafe;
import ghost606.networkcraft.inventory.ContainerUserManager;
import ghost606.networkcraft.tileentities.TileEntityRightManager;
import ghost606.networkcraft.tileentities.TileEntitySafe;
import ghost606.networkcraft.tileentities.TileEntityUserManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(Networkcraft.instance,
				this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te != null) {
			switch (ID) {
			case 0:
				if (te instanceof TileEntitySafe) {
					return new ContainerSafe(player.inventory,
							(TileEntitySafe) te);
				}
			case 1:
				if (te instanceof TileEntityRightManager) {
					return new ContainerRightManager(player.inventory,
							(TileEntityRightManager) te);
				}
			case 2:
				if (te instanceof TileEntityUserManager) {
					return new ContainerUserManager(player.inventory,
							(TileEntityUserManager) te);
				}
			}
		}
		return null;

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te != null) {
			switch (ID) {
			case 0:
				if (te instanceof TileEntitySafe) {
					return new GuiSafe(player.inventory, (TileEntitySafe) te);
				}
			case 1:
				if (te instanceof TileEntityRightManager) {
					return new GuiRightManager(player.inventory,
							(TileEntityRightManager) te);
				}
			case 2:
				if (te instanceof TileEntityUserManager) {
					return new GuiUserManager(player.inventory,
							(TileEntityUserManager) te);
				}
			}
		}
		return null;
	}
}
