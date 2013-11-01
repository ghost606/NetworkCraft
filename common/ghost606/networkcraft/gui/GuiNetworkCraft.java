package ghost606.networkcraft.gui;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ghost606.networkcraft.gui.elements.tab.TabCollection;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

@SideOnly(Side.CLIENT)
public class GuiNetworkCraft extends GuiContainer {
	public static final SoundManager guiSoundManager = FMLClientHandler.instance().getClient().sndManager;
	protected TabCollection tabs;
	
	public GuiNetworkCraft(Container container)
	{
		super(container);
		
		this.tabs = new TabCollection();
	}
	
	public TabCollection getTabCollection()
	{
		return tabs;
	}
	
	public int getXSize()
	{
		return xSize;
	}
	public int getYSize()
	{
		return xSize;
	}
	
	public int getOffsetLeft()
	{
		return guiLeft;
	}
	
	public int getOffsetTop()
	{
		return guiTop;
	}
	
	public FontRenderer getFontRenderer()
	{
		return fontRenderer;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		this.tabs.initGui();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.tabs.draw();
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.tabs.collide(mouseX, mouseY, mouseButton);
	}
}
