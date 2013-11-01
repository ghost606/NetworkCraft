package ghost606.networkcraft.gui.elements.tab;

import org.lwjgl.opengl.GL11;

import ghost606.networkcraft.gui.GuiNetworkCraft;
import ghost606.networkcraft.gui.elements.IGuiElement;
import ghost606.networkcraft.resources.ResourceManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTab extends Gui implements IGuiElement {
	protected GuiNetworkCraft gui;
	protected int backgroundColor = 0xffffff;
	protected int x;
	protected int y;
	protected int maxWidth = 104;
	protected int maxHeight = 124;

	private boolean open;
	private static final int minWidth = 24;
	private static final int minHeight = 24;
	private int currentWidth;
	private int currentHeight;

	public GuiTab(GuiNetworkCraft gui, int x, int y) {
		this.gui = gui;
		this.currentHeight = minHeight;
		this.currentWidth = minWidth;
		this.x = x;
		this.y = y;
	}

	public GuiNetworkCraft getGUI() {
		return gui;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void initGui() {

	}

	public int getHeight() {
		return this.currentHeight;
	}

	public int getWidth() {
		return this.currentWidth;
	}

	public void setFullyOpen() {
		this.open = true;
		this.currentWidth = this.maxWidth;
		this.currentHeight = this.maxHeight;
	}
	
	public void toggle() {
		if (this.open) {
			this.open = false;
		} else {
			this.gui.getTabCollection().closeOtherTabs(this);
			this.open = true;
		}
	}

	public boolean isOpen() {
		return this.open;
	}

	public boolean isFullyOpened() {
		return this.currentWidth >= this.maxWidth;
	}

	public void update() {
		// Width
		if (this.open && this.currentWidth < this.maxWidth) {
			this.currentWidth += 8;
		} else if (!this.open && this.currentWidth > minWidth) {
			this.currentWidth -= 8;
		}

		// Height
		if (this.open && this.currentHeight < this.maxHeight) {
			this.currentHeight += 8;
		} else if (!this.open && this.currentHeight > minHeight) {
			this.currentHeight -= 8;
		}
		this.gui.getTabCollection().updateLocation(this);
	}

	@Override
	public Boolean collide(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0) {
			mouseX = mouseX - this.gui.getOffsetLeft();
			mouseY = mouseY - this.gui.getOffsetTop();
			if (mouseX >= this.x && mouseX <= this.x + this.currentWidth
					&& mouseY >= this.y
					&& mouseY <= this.y + this.currentHeight) {
				Minecraft.getMinecraft().sndManager.playSoundFX("random.click",
						1.0F, 1.0F);
				this.toggle();
				return true;
			}
		}
		return false;
	}

	public void drawIcon(Icon icon, int x, int y) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
		drawTexturedModelRectFromIcon(x, y, icon, 16, 16);
	}
	
	public void drawIcon(Icon icon, int x, int y, int width, int height) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
		drawTexturedModelRectFromIcon(x, y, icon, width, height);
	}

	@Override
	public void draw() {
		float colorR = (this.backgroundColor >> 16 & 255) / 255.0F;
		float colorG = (this.backgroundColor >> 8 & 255) / 255.0F;
		float colorB = (this.backgroundColor & 255) / 255.0F;

		GL11.glColor4f(colorR, colorG, colorB, 1.0F);

		Minecraft.getMinecraft().renderEngine
				.bindTexture(ResourceManager.Gui_Textures.Tab);
		drawTexturedModalRect(this.x, this.y, 0, 256 - this.currentHeight, 4,
				this.currentHeight);
		drawTexturedModalRect(this.x + 4, this.y, 256 - this.currentWidth + 4,
				0, currentWidth - 4, 4);
		drawTexturedModalRect(this.x, this.y, 0, 0, 4, 4);
		drawTexturedModalRect(this.x + 4, this.y + 4,
				256 - this.currentWidth + 4, 256 - this.currentHeight + 4,
				this.currentWidth - 4, this.currentHeight - 4);

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
	}
}
