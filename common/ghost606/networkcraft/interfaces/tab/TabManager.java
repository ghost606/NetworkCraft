package ghost606.networkcraft.interfaces.tab;

import ghost606.networkcraft.interfaces.GuiNetworkCraft;

import java.util.ArrayList;
import java.util.List;

public class TabManager {
	private List<GuiTab> tabs = new ArrayList<GuiTab>();
	private GuiNetworkCraft gui;
	
	public TabManager(GuiNetworkCraft gui)
	{
		this.gui = gui;
	}
	
	public void add(GuiTab tab)
	{
		this.tabs.add(tab);
	}
	
	private GuiTab getAtPosition(int mouseX, int mouseY) {
		int xShift = this.gui.getOffsetLeft() + this.gui.getXSize();
		int yShift = this.gui.getOffsetTop() + 8;
		
		for (int i = 0; i < this.tabs.size(); i++)
		{
			GuiTab tab = this.tabs.get(i);

			if (tab.intersectsWith(mouseX, mouseY, xShift, yShift))
			{
				return tab;
			}
		}

		return null;
	}
	
	public void draw(int mouseX, int mouseY)
	{
		for (int i = 0; i < this.tabs.size(); i++)
		{
			GuiTab tab = this.tabs.get(i);
			tab.update();
			tab.draw(this.gui.getXSize(), 8);
		}
	}
	public void handleMouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		if (mouseButton == 0)
		{

			GuiTab tab = getAtPosition(mouseX, mouseY);
			if (tab != null)
			{
				tab.toggleOpen();		
			}
		}
	}
}
