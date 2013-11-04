package ghost606.networkcraft.tileentities;

import ghost606.networkcraft.information.BlockInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntitySafe extends TileEntityNetworkCraft implements IInventory {

	private ItemStack[] items;
	private int ticksSinceSync;
	private int numUsingPlayers;
	
	public float prevLidAngle;
	public float lidAngle;
	public static final int INVENTORY_SIZE = 6 * 11;
	public TileEntitySafe() {
		super();
		items = new ItemStack[INVENTORY_SIZE];
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}
	
	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemStack = getStackInSlot(i);

		if (itemStack != null) {
			if (itemStack.stackSize <= count) {
				setInventorySlotContents(i, null);
			} else {
				itemStack = itemStack.splitStack(count);
				onInventoryChanged();
			}
		}

		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;
		onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return BlockInfo.Safe.NAME;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
				zCoord + 0.5) <= 64;
	}

	@Override
	public boolean receiveClientEvent(int eventID, int numUsingPlayers) {
		 if (eventID == 1) {
	            this.numUsingPlayers = numUsingPlayers;
	            return true;
	        }
	        else
	            return super.receiveClientEvent(eventID, numUsingPlayers);
	}

	@Override
	public void openChest() {
		++numUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockInfo.Safe.ID, 1, numUsingPlayers);
	}

	@Override
	public void closeChest() {
		if (worldObj == null) return;
        --numUsingPlayers;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockInfo.Safe.ID, 1, numUsingPlayers);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (++ticksSinceSync % 20 * 4 == 0) {
            worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockInfo.Safe.ID, 1, numUsingPlayers);
        }

		this.prevLidAngle = this.lidAngle;
		float angleIncrement = 0.1F;
        double adjustedXCoord, adjustedZCoord;

        if (numUsingPlayers > 0 && lidAngle == 0.0F) {
            adjustedXCoord = xCoord + 0.5D;
            adjustedZCoord = zCoord + 0.5D;
            worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (numUsingPlayers == 0 && lidAngle > 0.0F || numUsingPlayers > 0 && lidAngle < 1.0F) {
            float var8 = lidAngle;

            if (numUsingPlayers > 0) {
                lidAngle += angleIncrement;
            }
            else {
                lidAngle -= angleIncrement;
            }

            if (lidAngle > 1.0F) {
                lidAngle = 1.0F;
            }

            if (lidAngle < 0.5F && var8 >= 0.5F) {
                adjustedXCoord = xCoord + 0.5D;
                adjustedZCoord = zCoord + 0.5D;
                worldObj.playSoundEffect(adjustedXCoord, yCoord + 0.5D, adjustedZCoord, "random.chestclosed", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (lidAngle < 0.0F) {
                lidAngle = 0.0F;
            }
        }
	}
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		NBTTagList items = new NBTTagList();

		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);
			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}
		compound.setTag("Items", items);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);

		NBTTagList items = compound.getTagList("Items");

		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound) items.tagAt(i);
			int slot = item.getByte("Slot");

			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot,
						ItemStack.loadItemStackFromNBT(item));
			}
		}
	}
	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub

	}
}
