package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.entity.client.KnightModel;
import io.github.heirofhope.mechanized_souls.entity.client.KnightRenderer;
import io.github.heirofhope.mechanized_souls.entity.client.ModModelLayers;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.EndRodParticle;
import org.quiltmc.loader.api.ModContainer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class MechanizedSoulsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient(ModContainer mod) {
		// Register the entity renderer and model layer
		EntityRendererRegistry.register(ModEntities.KNIGHT, KnightRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.KNIGHT, KnightModel::getTexturedModelData);

		// Register the particle factory
		ParticleFactoryRegistry.getInstance().register(FabricDocsReference.SOUL_LINK_PARTICLE, EndRodParticle.Factory::new);
	}
}
