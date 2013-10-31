package ghost606.networkcraft.container;

import ghost606.networkcraft.tileentities.TileEntityUserManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerUserManager extends Container {
	
	private TileEntityUserManager userManager;
	
	public ContainerUserManager(InventoryPlayer invPlayer, TileEntityUserManager userManager)
	{
		this.userManager = userManager;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.userManager.isUseableByPlayer(entityplayer);
	}

}
