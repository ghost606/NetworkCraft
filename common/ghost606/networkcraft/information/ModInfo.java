package ghost606.networkcraft.information;

public class ModInfo {
	public static final String ModId = "networkcraft";
	public static final String ModName = "Networkcraft";
	public static final String ModVersion = "0.0.4";
	public static final String ModChannel = "NC";

	public static class Textures {
		public static final String ROOTFOLDER = "networkcraft";
		public static final String GUI = "textures/gui";
		public static final String BLOCKS = "textures/blocks";
	}

	public static class NBT {
		public static final String TE_DIRECTION_KEY = "teDirection";
	}

	public static class GuiID {
		public static final int SAFE = 0;
		public static final int RIGHTMANAGER = 1;
		public static final int USERMANAGER = 2;
	}

	public static class Sound {
		public static final String CHEST_OPEN = "random.chestopen";
		public static final String CHEST_CLOSE = "random.chestclosed";
	}
}
