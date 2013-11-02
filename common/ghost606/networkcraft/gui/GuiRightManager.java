package ghost606.networkcraft.gui;

import ghost606.networkcraft.container.ContainerRightManager;
import ghost606.networkcraft.resources.ResourceManager;
import ghost606.networkcraft.tileentities.TileEntityRightManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GuiRightManager extends GuiNetworkCraft {
	
	public GuiRightManager(InventoryPlayer invPlayer, TileEntityRightManager rightManager) {
		super(new ContainerRightManager(invPlayer, rightManager));
		xSize = 214;
		ySize = 175;
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1,1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.Gui_Textures.RightManager);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		fontRenderer.drawString("Rights manager", 24, 6, 0x404040);
	}
}
