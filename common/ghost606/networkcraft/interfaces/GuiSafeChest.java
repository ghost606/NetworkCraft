package ghost606.networkcraft.interfaces;

import ghost606.networkcraft.tileentities.TileEntitySafeChest;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiSafeChest extends GuiContainer {

	public GuiSafeChest(InventoryPlayer invPlayer, TileEntitySafeChest safeChest) {
		super(new ContainerSafeChest(invPlayer, safeChest));
		
		xSize = 176;
		ySize = 222;
	}
	private static final ResourceLocation texture = new ResourceLocation("networkcraft", "textures/gui/safechest.png");


	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1,1);
		
		Minecraft.getMinecraft().func_110434_K().func_110577_a(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
