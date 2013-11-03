package ghost606.networkcraft.blocks;

import static net.minecraftforge.common.ForgeDirection.DOWN;
import ghost606.networkcraft.Networkcraft;
import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.tileentities.TileEntitySafe;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSafe extends BlockChest {

	public BlockSafe(int id) {
		super(id, -1);
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
		if (world.isRemote) {
			return true;
		} else {
			IInventory iinventory = this.getInventory(world, x, y, z);

			if (iinventory != null) {
				player.displayGUIChest(iinventory);
			}

			return true;
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
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x,
			int y, int z) {
		if (blockAccess.getBlockId(x, y, z - 1) == this.blockID) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
		} else if (blockAccess.getBlockId(x, y, z + 1) == this.blockID) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
		} else if (blockAccess.getBlockId(x - 1, y, z) == this.blockID) {
			this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		} else if (blockAccess.getBlockId(x + 1, y, z) == this.blockID) {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
		} else {
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F,
					0.9375F);
		}
	}

	@Override
	public IInventory getInventory(World world, int x, int y, int z) {
		Object object = (TileEntitySafe) world.getBlockTileEntity(x, y, z);

		if (object == null) {
			return null;
		} else if (world.isBlockSolidOnSide(x, y + 1, z, DOWN)) {
			return null;
		} else if (isOcelotBlockingChest(world, x, y, z)) {
			return null;
		}
		return (IInventory) object;
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
}
