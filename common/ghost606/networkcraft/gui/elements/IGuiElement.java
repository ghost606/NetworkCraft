package ghost606.networkcraft.gui.elements;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IGuiElement {
	public void initGui();
	public void draw();
	public Boolean collide(int mouseX, int mouseY, int mouseButton);
}
