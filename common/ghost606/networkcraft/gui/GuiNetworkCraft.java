package ghost606.networkcraft.gui;

import ghost606.networkcraft.gui.elements.tab.TabCollection;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiNetworkCraft extends GuiContainer {
	protected TabCollection tabs;
	
	public GuiNetworkCraft(Container container)
	{
		super(container);
	}

	public int getXSize()
	{
		return xSize;
	}
	public int getYSize()
	{
		return xSize;
	}
	
	public int getOffsetLeft()
	{
		return guiLeft;
	}
	
	public int getOffsetTop()
	{
		return guiTop;
	}
	
	public FontRenderer getFontRenderer()
	{
		return fontRenderer;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		this.tabs = new TabCollection();
		this.tabs.initGui();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.tabs.draw();
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.tabs.collide(mouseX, mouseY, mouseButton);
	}
}
