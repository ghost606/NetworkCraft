package ghost606.networkcraft.gui.elements.tab;

import ghost606.networkcraft.gui.GuiNetworkCraft;
import ghost606.networkcraft.gui.elements.listview.ListView;
import ghost606.networkcraft.resources.IconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class UserManagerTab extends GuiTab {
	private ListView list;

	public UserManagerTab(GuiNetworkCraft gui, int x, int y) {
		super(gui, x, y);
		this.backgroundColor = 0xff0000;
		this.list = new ListView(gui, this.x + 20, this.y + 5, 78, 73);
	}

	@Override
	public void initGui() {
		this.list.addElement("UM 1");
		this.list.addElement("UM 2");
		this.list.addElement("UM 3");
		this.list.addElement("UM 4");
		this.list.addElement("UM 5");

		super.initGui();
	}

	@Override
	public void draw() {
		super.draw();
		drawIcon(IconProvider.getInstance().getIcon(IconProvider.Access_Private), this.x + 3, this.y + 4);
		if (this.isFullyOpened()) {
			this.list.draw();
		}
	}

	@Override
	public Boolean collide(int mouseX, int mouseY, int mouseButton) {
		if (this.isFullyOpened()) {
			if (this.list.collide(mouseX, mouseY, mouseButton)) {
				return true;
			}
		}
		return super.collide(mouseX, mouseY, mouseButton);
	}
}
