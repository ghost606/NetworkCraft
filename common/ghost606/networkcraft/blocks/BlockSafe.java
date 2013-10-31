package ghost606.networkcraft.blocks;

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
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;

public class BlockSafe extends BlockChest {

	public BlockSafe(int id)
	{
		super(id, -1);
		this.setHardness(3.0F);
		this.setCreativeTab(Networkcraft.tabNetworkcraft);
		this.setUnlocalizedName(BlockInfo.Safe.UNLOCALIZED_NAME);
		this.disableStats();
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntitySafe();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, Networkcraft.instance, 0, world, x, y, z);
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta)
	{
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
	public void unifyAdjacentChests(World par1World, int par2, int par3, int par4)
	{

	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return true;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		world.markBlockForUpdate(x, y, z);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 22;
	}
}
