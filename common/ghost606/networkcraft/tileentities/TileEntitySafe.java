package ghost606.networkcraft.tileentities;

import ghost606.networkcraft.information.BlockInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntitySafe extends TileEntityChest implements IInventory {

	private ItemStack[] items;
	private static final float openSpeed = 0.1F;
	
	public TileEntitySafe()
	{
		items = new ItemStack[54];
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
		
		if (itemStack != null)
		{
			if (itemStack.stackSize <= count)
			{
				setInventorySlotContents(i, null);
			}
			else
			{
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
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		this.prevLidAngle = this.lidAngle;
		if (this.numUsingPlayers > 0)
		{
			if (this.lidAngle == 0.0F)
			{
				 double x = (double)this.xCoord + 0.5D;
				 double z = (double)this.zCoord + 0.5D;
				 this.worldObj.playSoundEffect(x, (double) yCoord + 0.5D, z, "random.chestopen", 0.5F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}
			this.lidAngle = Math.min(this.lidAngle + openSpeed, 1.0F);
		}
		else
		{
			if (this.lidAngle == 1.0F)
			{
				double x = (double)this.xCoord + 0.5D;
	            double z = (double)this.zCoord + 0.5D;
	            this.worldObj.playSoundEffect(x, (double)this.yCoord + 0.5D, z, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}
			this.lidAngle = Math.max(this.lidAngle - openSpeed, 0.0F);
		}
	}
	
	@Override
	public boolean receiveClientEvent(int id, int i) {
		if (id == 1)
        {
            numUsingPlayers = i;
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
		
		for (int i = 0; i < getSizeInventory(); i++)
		{
			ItemStack stack = getStackInSlot(i);
			if (stack != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
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
		
		for (int i = 0; i < items.tagCount(); i++)
		{
			NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
			int slot = item.getByte("Slot");
			
			if (slot >= 0 && slot < getSizeInventory())
			{
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}
}
