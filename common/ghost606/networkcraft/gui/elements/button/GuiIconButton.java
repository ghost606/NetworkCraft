package ghost606.networkcraft.gui.elements.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

public class GuiIconButton extends GuiButton {
	
	private Icon icon;
	private String tooltip;
	
	public GuiIconButton(int x, int y, int width, int height, String tooltipText, Icon icon) {
		super(0, x, y, width, height, "");
		this.icon = icon;
		this.tooltip = tooltipText;
	}
	
	private void drawIcon(Icon icon)
	{
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
		drawTexturedModelRectFromIcon(this.xPosition + 2, this.yPosition + 2, icon, 16, 16);
	}

	@Override
	public void drawButton(Minecraft minecraft, int x, int y) {
		super.drawButton(minecraft, x, y);
		
		drawIcon(this.icon);
	}
}
