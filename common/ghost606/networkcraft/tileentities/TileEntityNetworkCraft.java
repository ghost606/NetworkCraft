package ghost606.networkcraft.tileentities;

import ghost606.networkcraft.information.ModInfo;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityNetworkCraft extends TileEntity {
	protected ForgeDirection orientation;

	public TileEntityNetworkCraft()
	{
		orientation = ForgeDirection.SOUTH;
	}
	
	public ForgeDirection getOrientation() {

		return orientation;
	}

	public void setOrientation(ForgeDirection orientation) {

		this.orientation = orientation;
	}

	public void setOrientation(int orientation) {

		this.orientation = ForgeDirection.getOrientation(orientation);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);

		if (nbtTagCompound.hasKey(ModInfo.NBT.TE_DIRECTION_KEY)) {
			this.orientation = ForgeDirection.getOrientation(nbtTagCompound
					.getByte(ModInfo.NBT.TE_DIRECTION_KEY));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);

		nbtTagCompound.setByte(ModInfo.NBT.TE_DIRECTION_KEY, (byte) orientation.ordinal());
	}

}
