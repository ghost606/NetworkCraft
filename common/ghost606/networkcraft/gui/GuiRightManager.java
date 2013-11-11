package ghost606.networkcraft.gui;

import ghost606.networkcraft.gui.elements.listview.IconList;
import ghost606.networkcraft.inventory.ContainerRightManager;
import ghost606.networkcraft.resources.textures.ResourceManager;
import ghost606.networkcraft.tileentities.TileEntityRightManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GuiRightManager extends GuiNetworkCraft {
	private IconList iconList;
	public GuiRightManager(InventoryPlayer invPlayer, TileEntityRightManager rightManager) {
		super(new ContainerRightManager(invPlayer, rightManager));
		xSize = 214;
		ySize = 175;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		iconList = new IconList(this, 24, 17, 165, 33, 5, true);
		iconList.addElement("Element1");
		iconList.addElement("Element2");
		iconList.addElement("Element3");
		iconList.addElement("Element4");
		iconList.addElement("Element5");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1, 1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.Gui_Textures.RightManager);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		fontRenderer.drawString("Right manager", 24, 6, 0x404040);
		iconList.draw();
	}
}
