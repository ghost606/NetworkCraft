package ghost606.networkcraft.tileentities;

import ghost606.networkcraft.information.BlockInfo;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;

public class TileEntitySafe extends TileEntity implements IInventory {

	private ItemStack[] items;
	private int ticksSinceSync;
	private byte chestFacing = 0;
	private int numUsingPlayers;
	public float prevLidAngle;
	public float lidAngle;
	
	public TileEntitySafe() {
		items = new ItemStack[66];
	}

	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}
	
	public int getChestFacing() {
		return chestFacing;
	}

	public void setChestFacing(byte chestFacing) {
		this.chestFacing = chestFacing;
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
		return "Safe chest";
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
	public void updateEntity() {
		super.updateEntity();
		++this.ticksSinceSync;
		float f;

		if (!this.worldObj.isRemote
				&& this.numUsingPlayers != 0
				&& (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
			this.numUsingPlayers = 0;
			f = 5.0F;
			List list = this.worldObj.getEntitiesWithinAABB(
					EntityPlayer.class,
					AxisAlignedBB.getAABBPool().getAABB(
							(double) ((float) this.xCoord - f),
							(double) ((float) this.yCoord - f),
							(double) ((float) this.zCoord - f),
							(double) ((float) (this.xCoord + 1) + f),
							(double) ((float) (this.yCoord + 1) + f),
							(double) ((float) (this.zCoord + 1) + f)));
			Iterator iterator = list.iterator();

			while (iterator.hasNext()) {
				EntityPlayer entityplayer = (EntityPlayer) iterator.next();

				if (entityplayer.openContainer instanceof ContainerChest) {
					IInventory iinventory = ((ContainerChest) entityplayer.openContainer)
							.getLowerChestInventory();

					if (iinventory == this
							|| iinventory instanceof InventoryLargeChest
							&& ((InventoryLargeChest) iinventory)
									.isPartOfLargeChest(this)) {
						++this.numUsingPlayers;
					}
				}
			}
		}

		this.prevLidAngle = this.lidAngle;
		f = 0.1F;
		double d0;

		if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F) {
			double d1 = (double) this.xCoord + 0.5D;
			d0 = (double) this.zCoord + 0.5D;

			this.worldObj.playSoundEffect(d1, (double) this.yCoord + 0.5D, d0,
					"random.chestopen", 0.5F,
					this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}

		if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F
				|| this.numUsingPlayers > 0 && this.lidAngle < 1.0F) {
			float f1 = this.lidAngle;

			if (this.numUsingPlayers > 0) {
				this.lidAngle += f;
			} else {
				this.lidAngle -= f;
			}

			if (this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}

			float f2 = 0.5F;

			if (this.lidAngle < f2 && f1 >= f2) {
				d0 = (double) this.xCoord + 0.5D;
				double d2 = (double) this.zCoord + 0.5D;

				this.worldObj.playSoundEffect(d0, (double) this.yCoord + 0.5D,
						d2, "random.chestclosed", 0.5F,
						this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}

			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}

	@Override
	public boolean receiveClientEvent(int i, int j) {
		if (i == 1)
        {
            numUsingPlayers = j;
        }
        else if (i == 2)
        {
        	chestFacing = (byte) j;
        }
        else if (i == 3)
        {
            chestFacing = (byte) (j & 0x7);
            numUsingPlayers = (j & 0xF8) >> 3;
        }
        return true;
	}

	@Override
	public void openChest() {
		if (worldObj == null) return;
        numUsingPlayers++;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockInfo.Safe.ID, 1, numUsingPlayers);
	}

	@Override
	public void closeChest() {
		if (worldObj == null) return;
        numUsingPlayers--;
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockInfo.Safe.ID, 1, numUsingPlayers);
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
		compound.setByte("facing", chestFacing);
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
		chestFacing = compound.getByte("facing");
	}
	public void rotateAround(ForgeDirection axis)
    {
        setChestFacing((byte)ForgeDirection.getOrientation(chestFacing).getRotation(axis).ordinal());
        worldObj.addBlockEvent(xCoord, yCoord, zCoord, BlockInfo.Safe.ID, 2, getChestFacing());
    }
	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub

	}
}
