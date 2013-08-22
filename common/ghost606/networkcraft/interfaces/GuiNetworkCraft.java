package ghost606.networkcraft.interfaces;

import ghost606.networkcraft.interfaces.tab.TabManager;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class GuiNetworkCraft extends GuiContainer {
	protected TabManager tabManager;
	
	public GuiNetworkCraft(Container container) {
		super(container);
		
		this.tabManager = new TabManager(this);
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
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.tabManager.draw(mouseX, mouseY);
	}
	
	@Override
	protected void mouseClicked(int x, int y, int mouseButton) {
		super.mouseClicked(x, y, mouseButton);
		this.tabManager.handleMouseClicked(x, y, mouseButton);
	}
}
