package ghost606.networkcraft.gui.elements.tab;

import ghost606.networkcraft.gui.GuiNetworkCraft;
import ghost606.networkcraft.gui.elements.listview.ListView;

public class UserTab extends GuiTab {
	private ListView list;
	
	public UserTab(GuiNetworkCraft gui, int x, int y)
	{
		super(gui, x, y);
		
		backgroundColor = 564812;
	}
	
	@Override
	public void initGui() {
		this.list = new ListView(gui, x + 25, y + 25, 80, 80);
		this.list.addElement("Test01");
		this.list.addElement("Test02");
		this.list.addElement("Test03");
		this.list.addElement("Test04");
		this.list.addElement("Test05");
	}
	
	@Override
	public void draw() {
		super.draw();
		if (this.isFullyOpened())
		{
			this.list.draw();
		}
	}
	
	@Override
	public Boolean collide(int mouseX, int mouseY, int mouseButton) {
		if (this.isFullyOpened())
		{
			if (this.list.collide(mouseX, mouseY, mouseButton))
			{
				return true;
			}
			else
			{
				return super.collide(mouseX, mouseY, mouseButton);
			}
		}
		else
		{
			return super.collide(mouseX, mouseY, mouseButton);
		}
	}
}
