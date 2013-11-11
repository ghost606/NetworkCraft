package ghost606.networkcraft.gui.elements.listview;

import java.util.ArrayList;
import java.util.List;

import ghost606.networkcraft.gui.GuiNetworkCraft;
import ghost606.networkcraft.gui.elements.IGuiElement;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.gui.Gui;

@SideOnly(Side.CLIENT)
public class IconList extends Gui implements IGuiElement {
	private GuiNetworkCraft gui;

	private int x;
	private int y;
	private int height;
	private int width;
	private int selected = 0;
	private int showCount;
	private boolean horizontal;

	private int cellWidth;
	private int cellHeight;
	private static int cellPaddingX = 1;
	private static int cellPaddingY = 1;
	private int cellSpacing = 0;

	private int totalToNextElement;
	
	private List<String> items;

	private static final int textColor = 14737632;
	private static final int selectedColor = 0xFFFF0000;

	public IconList(GuiNetworkCraft gui, int x, int y, int width, int height,
			boolean horizontal) {
		this.items = new ArrayList<String>();
		this.gui = gui;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.horizontal = horizontal;
	}

	public IconList(GuiNetworkCraft gui, int x, int y, int width, int height,
			int showCount, boolean horizontal) {
		this(gui, x, y, width, height, horizontal);
		this.showCount = showCount;

		if (this.horizontal) {
			this.cellWidth = this.width / this.showCount;
			this.cellHeight = this.height;
		} else {
			this.cellHeight = this.height / this.showCount;
			this.cellWidth = this.width;
		}
		this.init();
	}

	public IconList(GuiNetworkCraft gui, int x, int y, int width, int height,
			int cellWidth, int cellHeight, boolean horizontal) {
		this(gui, x, y, width, height, horizontal);
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		if (this.horizontal) {
			this.showCount = this.width / this.cellWidth;
		} else {
			this.showCount = this.height / this.cellHeight;
		}
		this.init();
	}

	public IconList(GuiNetworkCraft gui, List<String> values, int x, int y,
			int width, int height, int count, boolean horizontal) {
		this(gui, x, y, width, height, count, horizontal);
		this.items = values;
	}

	public IconList(GuiNetworkCraft gui, List<String> values, int x, int y,
			int width, int height, int cellWidth, int cellHeight,
			boolean horizontal) {
		this(gui, x, y, width, height, cellWidth, cellHeight, horizontal);
		this.items = values;
	}

	private void init()
	{
		this.totalToNextElement = this.cellWidth + this.cellSpacing;
	}
	
	public void addElement(String element) {
		this.items.add(element);
	}
	
	public int getCellSpacing() {
		return cellSpacing;
	}

	public void setCellSpacing(int cellSpacing) {
		this.cellSpacing = cellSpacing;
		init();
	}
	@Override
	public void draw() {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int xPos;
		int cellPaddingXTop = cellPaddingX * 2;
		int cellPaddingYTop = cellPaddingY * 2;
		for (int i = 0; i < this.items.size(); i++) {
			if (i > this.showCount - 1) {
				break;
			}
			xPos = this.x + this.totalToNextElement * i;
			drawRect(xPos + cellPaddingX, this.y + cellPaddingY, xPos + this.cellWidth, this.y + this.cellHeight, selectedColor);
//			this.gui.getFontRenderer().drawString(this.items.get(i),
//			this.x + rowPadding + (lineWidth + selectPadding + 1) * i,
//			this.y + rowPadding, textColor);
		}
	}

	@Override
	public Boolean collide(int mouseX, int mouseY, int mouseButton) {
		return false;
	}

	@Override
	public void initGui() {
		// TODO Auto-generated method stub
		
	}
}
