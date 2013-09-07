package ghost606.networkcraft.gui.elements.button;

import ghost606.networkcraft.resources.IconProvider;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;

public class GuiIconButton extends GuiButton {

	private static enum State { Inactive, Active, Highlight }
	private State state = State.Inactive;
	private Icon icon;
	
	public GuiIconButton(int id, int x, int y, int width, int height, String text, Icon icon) {
		super(id, x, y, width, height, text);
		this.icon = icon;
	}
	
	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		switch (state)
		{
		case Inactive:
			drawIcon(IconProvider.getInstance().getIcon(IconProvider.Button_Inactive), this.xPosition, this.yPosition);
			break;
		case Active:
			drawIcon(IconProvider.getInstance().getIcon(IconProvider.Button), this.xPosition, this.yPosition);
			break;
		case Highlight:
			drawIcon(IconProvider.getInstance().getIcon(IconProvider.Button_Highlight), this.xPosition, this.yPosition);
			break;
		default:
			break;
		}
		
		drawIcon(this.icon, this.xPosition, this.yPosition);
	}
	
	private void drawIcon(Icon icon, int x, int y)
	{
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
		drawTexturedModelRectFromIcon(x, y, icon, 16, 16);
		Minecraft.getMinecraft().func_110434_K().func_110577_a(TextureMap.field_110576_c);
	}
	
	@Override
	public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
		Minecraft.getMinecraft().sndManager.playSoundFX("random.click", 1.0F, 0.8F);
		state = State.Active;
		return super.mousePressed(minecraft, mouseX, mouseY);
	}
	
	@Override
	protected void mouseDragged(Minecraft minecraft, int mouseX, int mouseY) {
		super.mouseDragged(minecraft, mouseX, mouseY);
		state = State.Highlight;
	}
}
