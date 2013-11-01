package ghost606.networkcraft.gui.elements.tab;

import net.minecraft.client.Minecraft;
import ghost606.networkcraft.gui.GuiNetworkCraft;
import ghost606.networkcraft.gui.elements.button.GuiIconButton;
import ghost606.networkcraft.resources.IconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class UserTab extends GuiTab {
	private GuiIconButton button[];
	public UserTab(GuiNetworkCraft gui, int x, int y) {
		super(gui, x, y);
		this.backgroundColor = 564812;
		this.maxHeight = 60;
		this.button = new GuiIconButton[3];
		this.button[0] = new GuiIconButton(gui.getOffsetLeft() + x + 22, y + 36, 20, 20, "Public", IconProvider.getInstance().getIcon(IconProvider.Access_Public));
		this.button[1] = new GuiIconButton(gui.getOffsetLeft() + x + 44, y + 36, 20, 20, "Private", IconProvider.getInstance().getIcon(IconProvider.Access_Private));
		this.button[2] = new GuiIconButton(gui.getOffsetLeft() + x + 66, y + 36, 20, 20, "User Manager", IconProvider.getInstance().getIcon(IconProvider.Access_UserManager));
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	public void draw() {
		super.draw();
		drawIcon(IconProvider.getInstance().getIcon(IconProvider.Access_Private), x + 3, y + 4);
		if (this.isFullyOpened()) {
			this.gui.getFontRenderer().drawStringWithShadow("Owner", x + 22, y + 6, 0xCCCC00);
			this.gui.getFontRenderer().drawString("Name", x + 22, y + 16, 0x000000);
			this.gui.getFontRenderer().drawStringWithShadow("Access", x + 22, y + 26, 0x404040);
			for (int i = 0; i < button.length; i++)
			{
				button[i].drawButton(Minecraft.getMinecraft(), 0, 0);
			}
		}
	}

	@Override
	public Boolean collide(int mouseX, int mouseY, int mouseButton) {
		if (this.isFullyOpened() && mouseButton == 0) {
			for (int i = 0; i < button.length; i++)
			{
				if (button[i].mousePressed(Minecraft.getMinecraft(), mouseX, mouseY))
				{
					return true;
				}
			}
		}
		return super.collide(mouseX, mouseY, mouseButton);
	}
}
