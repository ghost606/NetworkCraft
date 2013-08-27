package ghost606.networkcraft.gui;

import ghost606.networkcraft.container.ContainerSafe;
import ghost606.networkcraft.entities.tileentities.TileEntitySafe;
import ghost606.networkcraft.gui.elements.tab.UserTab;
import ghost606.networkcraft.resources.ResourceManager;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSafe extends GuiNetworkCraft {

	public GuiSafe(InventoryPlayer invPlayer, TileEntitySafe safeChest) {
		super(new ContainerSafe(invPlayer, safeChest));
		
		xSize = 176;
		ySize = 222;
	}
	
	@Override
	public void initGui() {
		GuiNetworkCraft gui = (GuiNetworkCraft)this;
		tabs.add(new UserTab(gui, this.getXSize(), 8));
		
		super.initGui();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1, 1, 1,1);
		
		Minecraft.getMinecraft().func_110434_K().func_110577_a(ResourceManager.Gui_Textures.SafeChest);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		fontRenderer.drawString("Safe chest", 8, 6, 0x404040);
	}
}
