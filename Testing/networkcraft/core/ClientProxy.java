package networkcraft.core;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderInformation() {
		MinecraftForgeClient.preloadTexture("/networkcraft/resources/blocks.png");
		MinecraftForgeClient.preloadTexture("/networkcraft/resources/items.png");
	}
}
