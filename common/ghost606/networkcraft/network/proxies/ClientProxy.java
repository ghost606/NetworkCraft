package ghost606.networkcraft.network.proxies;

import cpw.mods.fml.client.registry.ClientRegistry;
import ghost606.networkcraft.rendering.RenderSafe;
import ghost606.networkcraft.tileentities.TileEntitySafe;

public class ClientProxy extends CommonProxy {

	public void initSounds() {
		
	}
	
	public void initRenders()
	{
		ClientRegistry.registerTileEntity(TileEntitySafe.class, "safe", new RenderSafe());
	}
}
