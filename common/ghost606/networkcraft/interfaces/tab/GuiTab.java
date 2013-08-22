package ghost606.networkcraft.interfaces.tab;

import ghost606.networkcraft.resources.ResourceManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTab extends Gui {
	private boolean open;
	protected int backgroundColor = 0xffffff;
	
	protected int maxWidth = 124;
	private int minWidth = 24;
	private int currentWidth;
	protected int maxHeight = 124;
	private int minHeight = 24;
	private int currentHeight;
	
	public GuiTab()
	{
		this.currentHeight = this.minHeight;
		this.currentWidth = this.minWidth;
	}
	
	public void update() {
		// Width
		if (this.open && this.currentWidth < this.maxWidth) {
			this.currentWidth += 4;
		} else if (!this.open && this.currentWidth > this.minWidth) {
			this.currentWidth -= 4;
		}

		// Height
		if (this.open && this.currentHeight < this.maxHeight) {
			this.currentHeight += 4;
		} else if (!this.open && this.currentHeight > this.minHeight) {
			this.currentHeight -= 4;
		}
	}

	public int getHeight()
	{
		return this.currentHeight;
	}
	
	public int getWidth()
	{
		return this.currentWidth;
	}
	
	public boolean intersectsWith(int mouseX, int mouseY, int x, int y)
	{
		if (mouseX >= x && mouseX <= x + this.currentWidth && mouseY >= y && mouseY <= y + this.currentHeight)
		{
			return true;
		}
		return false;
	}

	public void setFullyOpen() {
		this.open = true;
		this.currentWidth = this.maxWidth;
		this.currentHeight = this.maxHeight;
	}

	public void toggleOpen() {
		if (this.open) {
			this.open = false;
		} else {
			this.open = true;
		}
	}

	public boolean isOpen() {
		return this.open;
	}

	public boolean isFullyOpened() {
		return this.currentWidth >= this.maxWidth;
	}

	public void drawIcon(Icon icon, int x, int y) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
		drawTexturedModelRectFromIcon(x, y, icon, 16, 16);
	}

	public void draw(int x, int y) {
		float colorR = (this.backgroundColor >> 16 & 255) / 255.0F;
		float colorG = (this.backgroundColor >> 8 & 255) / 255.0F;
		float colorB = (this.backgroundColor & 255) / 255.0F;

		GL11.glColor4f(colorR, colorG, colorB, 1.0F);

		Minecraft.getMinecraft().renderEngine.func_110577_a(ResourceManager.TAB_TEXTURE);
		drawTexturedModalRect(x, y, 0, 256 - this.currentHeight, 4, this.currentHeight);
		drawTexturedModalRect(x + 4, y, 256 - this.currentWidth + 4, 0, currentWidth - 4, 4);
		drawTexturedModalRect(x, y, 0, 0, 4, 4);
		drawTexturedModalRect(x + 4, y + 4, 256 - this.currentWidth + 4, 256 - this.currentHeight + 4, this.currentWidth - 4, this.currentHeight - 4);

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
	}
}
