package ghost606.networkcraft.gui.elements.listview;

import java.util.ArrayList;
import java.util.List;

import ghost606.networkcraft.gui.GuiNetworkCraft;
import ghost606.networkcraft.gui.elements.IGuiElement;
import ghost606.networkcraft.resources.ResourceManager;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

@SideOnly(Side.CLIENT)
public class VListView extends Gui implements IGuiElement {

	private GuiNetworkCraft gui;
	private int x;
	private int y;
	private int height;
	private int width;
	private int rows;
	private int selected = 2;

	private List<String> items;

	private static final int textColor = 14737632;
	private static final int selectedColor = -16777216;
	private static final int lineHeight = 11;
	private static final int rowPadding = 5;
	private static final int selectPadding = 2;

	public VListView(GuiNetworkCraft gui, int x, int y, int width, int height) {
		this.gui = gui;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;

		this.items = new ArrayList<String>();
		this.rows = height / lineHeight;
	}

	public void addElement(String element) {
		this.items.add(element);
	}

	@Override
	public void initGui() {

	}

	@Override
	public void draw() {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		Minecraft.getMinecraft().renderEngine
				.bindTexture(ResourceManager.Gui_Textures.ListView);
		drawTexturedModalRect(this.x, this.y, 0, 256 - this.height, 1,
				this.height);
		drawTexturedModalRect(this.x + 1, this.y, 256 - this.width + 1, 0,
				this.width - 1, 1);
		drawTexturedModalRect(this.x + 1, this.y + 1, 256 - this.width + 1,
				256 - this.height + 1, this.width - 1, this.height - 1);

		for (int i = 0; i < this.items.size(); i++) {
			if (i > this.rows - 1) {
				break;
			}
			if (this.selected == i) {
				int yLocation = this.y + selectPadding
						+ (selectPadding + 1 + lineHeight) * i;
				drawRect(x + selectPadding, yLocation, this.x + this.width
						- selectPadding,
						yLocation + lineHeight + selectPadding, selectedColor);
			}
			this.gui.getFontRenderer().drawString(this.items.get(i),
					this.x + rowPadding,
					this.y + rowPadding + (lineHeight + selectPadding + 1) * i,
					textColor);
		}
	}

	public void drawIcon() {

	}

	@Override
	public Boolean collide(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0) {
			mouseX = mouseX - this.gui.getOffsetLeft();
			mouseY = mouseY - this.gui.getOffsetTop();
			if (mouseX >= this.x && mouseX <= this.x + this.width
					&& mouseY >= this.y && mouseY <= this.y + this.height) {
				int index = (mouseY - this.y - selectPadding)
						/ (lineHeight + 1 + selectPadding);
				if (index < this.items.size()) {
					Minecraft.getMinecraft().sndManager.playSoundFX(
							"random.click", 1.0F, 0.8F);
					this.selected = index;
				}

				return true;
			}
		}
		return false;
	}
}
