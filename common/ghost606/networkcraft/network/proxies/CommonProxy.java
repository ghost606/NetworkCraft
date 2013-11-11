package ghost606.networkcraft.network.proxies;

import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.tileentities.TileEntityRightManager;
import ghost606.networkcraft.tileentities.TileEntitySafe;
import ghost606.networkcraft.tileentities.TileEntityUserManager;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	public void registerSoundHandler() {

	}

	public void initRenderingAndTextures() {

	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySafe.class, BlockInfo.Safe.TE_NAME);
		GameRegistry.registerTileEntity(TileEntityRightManager.class, BlockInfo.RightManager.TE_NAME);
		GameRegistry.registerTileEntity(TileEntityUserManager.class, BlockInfo.UserManager.TE_NAME);
	}
}
