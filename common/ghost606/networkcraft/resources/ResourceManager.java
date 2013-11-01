package ghost606.networkcraft.resources;

import ghost606.networkcraft.information.ModInfo;
import net.minecraft.util.ResourceLocation;

public class ResourceManager {
	
	public static final class Gui_Textures
	{
		public static final ResourceLocation Tab = new ResourceLocation(ModInfo.Textures.ROOTFOLDER, ModInfo.Textures.GUI + "/tab.png");
		public static final ResourceLocation ListView = new ResourceLocation(ModInfo.Textures.ROOTFOLDER, ModInfo.Textures.GUI + "/listview.png");
		public static final ResourceLocation SafeChest = new ResourceLocation(ModInfo.Textures.ROOTFOLDER, ModInfo.Textures.GUI + "/safe.png");
		public static final ResourceLocation UserManager = new ResourceLocation(ModInfo.Textures.ROOTFOLDER, ModInfo.Textures.GUI + "/usermanager.png");
	}
	public static final class Block_Textures
	{
		public static final ResourceLocation Safe = new ResourceLocation(ModInfo.Textures.ROOTFOLDER, ModInfo.Textures.BLOCKS + "/safe.png");
		public static final ResourceLocation UserManager = new ResourceLocation(ModInfo.Textures.ROOTFOLDER, ModInfo.Textures.BLOCKS + "/iron_block.png");
	}
}
