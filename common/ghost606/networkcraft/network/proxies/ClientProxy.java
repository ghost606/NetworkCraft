package ghost606.networkcraft.network.proxies;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.client.MinecraftForgeClient;

import ghost606.networkcraft.client.item.itemSafeRenderer;
import ghost606.networkcraft.client.renderer.TileEntitySafeRender;
import ghost606.networkcraft.information.BlockInfo;
import ghost606.networkcraft.tileentities.TileEntitySafe;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerSoundHandler() {
		super.registerSoundHandler();
	}

	@Override
	public void initRenderingAndTextures() {
		super.initRenderingAndTextures();
		BlockInfo.Safe.RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
		MinecraftForgeClient.registerItemRenderer(BlockInfo.Safe.ID,
				new itemSafeRenderer());
	}
	@Override
	public void registerTileEntities() {
		super.registerTileEntities();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySafe.class,
				new TileEntitySafeRender());
	}
}
