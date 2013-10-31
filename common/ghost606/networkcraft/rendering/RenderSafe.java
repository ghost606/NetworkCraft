package ghost606.networkcraft.rendering;

import ghost606.networkcraft.resources.ResourceManager;
import ghost606.networkcraft.tileentities.TileEntitySafe;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderSafe extends TileEntitySpecialRenderer {
	
	private static final ModelChest MODEL = new ModelChest();
	
	public void renderTileEntityChest(TileEntitySafe tileEntity, double x, double y, double z, float partialTick)
	{
		ModelChest modelChest = MODEL;
		bindTexture(ResourceManager.Block_Textures.Safe);
		
		GL11.glPushMatrix();
		GL11.glEnable(32826);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GL11.glScalef(1.0F, - 1.0F, - 1.0F);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(- 0.5F, - 0.5F, - 0.5F);
		

		float lidangle = tileEntity.prevLidAngle + (tileEntity.lidAngle - tileEntity.prevLidAngle) * partialTick;
		
        lidangle = 1.0F - lidangle;
        lidangle = 1.0F - lidangle * lidangle * lidangle;
        
        modelChest.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
        modelChest.renderAll();
        
		GL11.glDisable(32826);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {
		
		renderTileEntityChest((TileEntitySafe) tileEntity, x, y, z, partialTick);
	}
}
