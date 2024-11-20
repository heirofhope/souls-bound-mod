package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.Entity.ModEntities;
import io.github.heirofhope.mechanized_souls.Entity.client.KnightModel;
import io.github.heirofhope.mechanized_souls.Entity.client.KnightRenderer;
import io.github.heirofhope.mechanized_souls.Entity.client.ModModelLayers;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
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
