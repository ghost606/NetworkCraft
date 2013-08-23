package ghost606.networkcraft.gui.elements.tab;

import ghost606.networkcraft.gui.elements.IGuiElement;

import java.util.ArrayList;
import java.util.List;

public class TabCollection implements IGuiElement {
	private List<GuiTab> tabs = new ArrayList<GuiTab>();
	
	public TabCollection()
	{
		
	}
	
	public void add(GuiTab tab)
	{
		this.tabs.add(tab);
	}
	
	@Override
	public void initGui() {
		for (int i = 0; i < this.tabs.size(); i++)
		{
			GuiTab tab = this.tabs.get(i);
			tab.initGui();
		}
	}
	
	@Override
	public void draw()
	{
		for (int i = 0; i < this.tabs.size(); i++)
		{
			GuiTab tab = this.tabs.get(i);
			tab.update();
			tab.draw();
		}
	}
	
	@Override
	public Boolean collide(int mouseX, int mouseY, int mouseButton)
	{
		for (int i = 0; i < this.tabs.size(); i++)
		{
			if (this.tabs.get(i).collide(mouseX, mouseY, mouseButton))
			{
				return true;
			}
		}
		return false;
	}
}
