package ghost606.networkcraft.inventory;

import ghost606.networkcraft.tileentities.TileEntitySafe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSafe extends Container {

	private TileEntitySafe tileEntitySafe;
	
	public ContainerSafe(InventoryPlayer invPlayer, TileEntitySafe safe)
	{
		this.tileEntitySafe = safe;
		this.tileEntitySafe.openChest();
		
		for (int x = 0; x < 9; x++)
		{
			addSlotToContainer(new Slot(invPlayer, x, 26 + 18 * x, 198));
		}
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 26 + 18 * x, 140 + y * 18));
			}
		}
		for (int y = 0; y < 6; y++)
		{
			for (int x = 0; x < 11; x++)
			{
				addSlotToContainer(new Slot(safe, x + y * 11, 8 + 18 * x, 18 + y * 18));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.tileEntitySafe.isUseableByPlayer(entityplayer);
	}
	
	@Override
    public void onContainerClosed(EntityPlayer entityPlayer) {

        super.onContainerClosed(entityPlayer);
        tileEntitySafe.closeChest();
    }

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = getSlot(i);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();
			
			if (i >= 36 )
			{
				if (!mergeItemStack(stack, 0, 36, false))
				{
					return null;
				}
			}
			else if (!mergeItemStack(stack, 36, 36 + this.tileEntitySafe.getSizeInventory(), false))
			{
				return null;
			}
			if (stack.stackSize == 0)
			{
				slot.putStack(null);
			}
			else
			{
				slot.onSlotChanged();
			}
			slot.onPickupFromSlot(player, stack);
			
			return result;
		}
		
		return null;
	}
}
