package ghost606.networkcraft.resources.textures;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class IconProvider {
	private static IconProvider instance;
	
	public static final int Access_UserManager = 0;
	public static final int Access_Private = 1;
	public static final int Access_Public = 2;
	public static final int Arrow_Up = 3;
	public static final int Arrow_Down = 4;
	
	public static final int Max = 5;
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	@SideOnly(Side.CLIENT)
	public static IconProvider getInstance()
	{
		if (instance == null)
		{
			instance = new IconProvider();
		}
		return instance;
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int iconIndex)
	{
		return icons[iconIndex];
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register)
	{
		icons = new Icon[IconProvider.Max];
		
		icons[IconProvider.Access_UserManager] = register.registerIcon("networkcraft:icons/Icon_Access_UserManager");
		icons[IconProvider.Access_Private] = register.registerIcon("networkcraft:icons/Icon_Access_Private");
		icons[IconProvider.Access_Public] = register.registerIcon("networkcraft:icons/Icon_Access_Public");
		icons[IconProvider.Arrow_Up] = register.registerIcon("networkcraft:icons/Icon_Arrow_Up");
		icons[IconProvider.Arrow_Down] = register.registerIcon("networkcraft:icons/Icon_Arrow_Down");

	}
}
