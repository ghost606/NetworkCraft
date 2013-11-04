package ghost606.networkcraft.inventory;

import ghost606.networkcraft.tileentities.TileEntityRightManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerRightManager extends Container {
	
	private TileEntityRightManager rightManager;
	
	public ContainerRightManager(InventoryPlayer invPlayer, TileEntityRightManager rightManager)
	{
		this.rightManager = rightManager;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.rightManager.isUseableByPlayer(entityplayer);
	}

}
