package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.entity.client.KnightRenderer;
import io.github.heirofhope.mechanized_souls.entity.custom.mechanized_souls;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class MechanizedSoulsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityRendererRegistry.register(ModEntities.KNIGHT, KnightRenderer::new);
	}
}

		@Override
		public void onInitializeClient() {
			EntityRendererRegistry.register(mechanized_souls.ChainEntityType, FlyingItemEntityRenderer::new);
			// older versions may have to use
    		/* EntityRendererRegistry.INSTANCE.register(ProjectileTutorialMod.PackedSnowballEntityType, (context) ->
    				 new FlyingItemEntityRenderer(context)); */

		}
	}
