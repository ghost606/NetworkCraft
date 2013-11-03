package ghost606.networkcraft.blocks;

import ghost606.networkcraft.Networkcraft;
import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.tileentities.TileEntitySafe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class BlockSafe extends BlockContainer {

	public BlockSafe(int id) {
		super(id, Material.iron);
		this.setHardness(3.0F);
		this.setCreativeTab(Networkcraft.tabNetworkcraft);
		this.setUnlocalizedName(BlockInfo.Safe.UNLOCALIZED_NAME);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntitySafe();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (te == null || !(te instanceof TileEntitySafe)) {
				return true;
			}

			if (world.isBlockSolidOnSide(x, y + 1, z, ForgeDirection.DOWN)) {
				return true;
			}

			FMLNetworkHandler.openGui(player, Networkcraft.instance, 0, world,
					x, y, z);
		}
		return true;
	}

	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack)
    {
        byte chestFacing = 0;
        int facing = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if (facing == 0)
        {
            chestFacing = 2;
        }
        if (facing == 1)
        {
            chestFacing = 5;
        }
        if (facing == 2)
        {
            chestFacing = 3;
        }
        if (facing == 3)
        {
            chestFacing = 4;
        }
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof TileEntitySafe)
        {
        	TileEntitySafe teic = (TileEntitySafe) te;
            teic.setChestFacing(chestFacing);
            world.markBlockForUpdate(x, y, z);
        }
    }	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te != null && te instanceof IInventory) {
			IInventory inventory = (IInventory) te;

			for (int i = 0; i < inventory.getSizeInventory(); i++) {
				ItemStack stack = inventory.getStackInSlotOnClosing(i);

				if (stack != null) {
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();

					Entity droppedItem = new EntityItem(world, spawnX, spawnY,
							spawnZ, stack);

					float mult = 0.05F;

					droppedItem.motionX = (-0.5F + world.rand.nextFloat())
							* mult;
					droppedItem.motionY = (4 + world.rand.nextFloat()) * mult;
					droppedItem.motionZ = (-0.5F + world.rand.nextFloat())
							* mult;

					world.spawnEntityInWorld(droppedItem);
				}
			}
		}
		super.breakBlock(world, x, y, z, id, meta);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		world.markBlockForUpdate(x, y, z);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 22;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z,
			ForgeDirection axis) {
		if (worldObj.isRemote)
        {
            return false;
        }
        if (axis == ForgeDirection.UP || axis == ForgeDirection.DOWN)
        {
            TileEntity tileEntity = worldObj.getBlockTileEntity(x, y, z);
            if (tileEntity instanceof TileEntitySafe) {
                TileEntitySafe icte = (TileEntitySafe) tileEntity;
                icte.rotateAround(axis);
            }
            return true;
        }
        return false;
    }
}
