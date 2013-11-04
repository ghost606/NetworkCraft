package ghost606.networkcraft.gui;

import ghost606.networkcraft.inventory.ContainerUserManager;
import ghost606.networkcraft.resources.ResourceManager;
import ghost606.networkcraft.tileentities.TileEntityUserManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GuiUserManager extends GuiNetworkCraft {
	
	public GuiUserManager(InventoryPlayer invPlayer, TileEntityUserManager userManager) {
		super(new ContainerUserManager(invPlayer, userManager));
		xSize = 214;
		ySize = 206;
	}
	
	@Override
	public void initGui() {
		super.initGui();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1,1);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.Gui_Textures.UserManager);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		fontRenderer.drawString("User manager", 24, 6, 0x404040);
	}
}
