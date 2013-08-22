package ghost606.networkcraft.interfaces;

import ghost606.networkcraft.tileentities.TileEntitySafeChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSafeChest extends Container {

	private TileEntitySafeChest safeChest;
	
	public ContainerSafeChest(InventoryPlayer invPlayer, TileEntitySafeChest safeChest)
	{
		this.safeChest = safeChest;
		
		for (int x = 0; x < 9; x++)
		{
			addSlotToContainer(new Slot(invPlayer, x, 8 + 18 * x, 198));
		}
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, 8 + 18 * x, 140 + y * 18));
			}
		}
		for (int y = 0; y < 6; y++)
		{
			for (int x = 0; x < 9; x++)
			{
				addSlotToContainer(new Slot(safeChest, x + y * 9, 8 + 18 * x, 18 + y * 18));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return safeChest.isUseableByPlayer(entityplayer);
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
			else if (!mergeItemStack(stack, 36, 36 + safeChest.getSizeInventory(), false))
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
