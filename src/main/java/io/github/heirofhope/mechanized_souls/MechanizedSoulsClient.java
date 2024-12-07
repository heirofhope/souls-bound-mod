package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.entity.client.KnightModel;
import io.github.heirofhope.mechanized_souls.entity.client.KnightRenderer;
<<<<<<< HEAD
=======
import io.github.heirofhope.mechanized_souls.entity.client.ModModelLayers;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
>>>>>>> parent of e9b198a (Merge branch 'main' into ParticleScytheStringTest)
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class MechanizedSoulsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityRendererRegistry.register(ModEntities.KNIGHT, KnightRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.KNIGHT, KnightModel::getTexturedModelData);

	}
}
