package ghost606.networkcraft.gui;

import ghost606.networkcraft.container.ContainerSafe;
import ghost606.networkcraft.gui.elements.tab.TabCollection;
import ghost606.networkcraft.gui.elements.tab.UserManagerTab;
import ghost606.networkcraft.gui.elements.tab.UserTab;
import ghost606.networkcraft.resources.ResourceManager;
import ghost606.networkcraft.tileentities.TileEntitySafe;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSafe extends GuiNetworkCraft {

	public GuiSafe(InventoryPlayer invPlayer, TileEntitySafe safeChest) {
		super(new ContainerSafe(invPlayer, safeChest));
		
		xSize = 211;
		ySize = 221;
		
		tabs.add(new UserTab((GuiNetworkCraft)this, this.getXSize(), TabCollection.yMargin + TabCollection.tabHeightClosed * this.tabs.getSize()));
		tabs.add(new UserManagerTab((GuiNetworkCraft)this, this.getXSize(), TabCollection.yMargin + TabCollection.tabHeightClosed * this.tabs.getSize()));
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1,1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.Gui_Textures.SafeChest);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		fontRenderer.drawString("Safe chest", 8, 6, 0x404040);
	}
}
