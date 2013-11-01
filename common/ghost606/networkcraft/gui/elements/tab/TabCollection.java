package ghost606.networkcraft.gui.elements.tab;

import ghost606.networkcraft.gui.elements.IGuiElement;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TabCollection implements IGuiElement {
	private List<GuiTab> tabs;
	public static final int tabHeightClosed = 26;
	public static final int yMargin = 8;

	public TabCollection() {
		tabs = new ArrayList<GuiTab>();
	}

	public void add(GuiTab tab) {
		this.tabs.add(tab);
	}

	public void clear() {
		tabs = new ArrayList<GuiTab>();
	}

	public int getSize() {
		return tabs.size();
	}

	public void closeOtherTabs(GuiTab tab) {
		int index = getTabIndex(tab);
		for (int i = 0; i < tabs.size(); i++) {
			if (i != index && tabs.get(i).isOpen()) {
				tabs.get(i).toggle();
			}
		}
	}

	public void updateLocation(GuiTab tab) {
		int index = getTabIndex(tab);
		if (index != -1 || index < tabs.size()) {
			int height = tabs.get(index).getHeight() - tabHeightClosed;
			for (int i = index + 1; i < tabs.size(); i++) {
				tabs.get(i).setY(yMargin + tabHeightClosed * i + height);
			}
		}
	}

	public int getTabIndex(GuiTab tab) {
		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i).equals(tab)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public void initGui() {
		for (int i = 0; i < this.tabs.size(); i++) {
			GuiTab tab = this.tabs.get(i);
			tab.initGui();
		}
	}

	@Override
	public void draw() {
		for (int i = 0; i < this.tabs.size(); i++) {
			GuiTab tab = this.tabs.get(i);
			tab.update();
			tab.draw();
		}

	}

	@Override
	public Boolean collide(int mouseX, int mouseY, int mouseButton) {
		for (int i = 0; i < this.tabs.size(); i++) {
			if (this.tabs.get(i).collide(mouseX, mouseY, mouseButton)) {
				return true;
			}
		}
		return false;
	}
}
