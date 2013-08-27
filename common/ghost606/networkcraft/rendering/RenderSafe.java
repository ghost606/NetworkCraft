package ghost606.networkcraft.rendering;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderSafe extends Render {

	public void doRenderSafe(Entity entity, double x, double y, double z, float d1, float f1)
	{
		
	}
	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void doRender(Entity entity, double x, double y, double z, float d1, float f1) {
		// TODO Auto-generated method stub
		doRenderSafe(entity, x, y, z, d1, f1);
	}

}
