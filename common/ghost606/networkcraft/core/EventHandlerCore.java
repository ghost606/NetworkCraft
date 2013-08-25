package ghost606.networkcraft.core;

import ghost606.networkcraft.resources.IconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class EventHandlerCore {
	
	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Pre event)
	{
		if (event.map.textureType == 1)
		{
			IconProvider.getInstance().registerIcons(event.map);
		}
	}
}
