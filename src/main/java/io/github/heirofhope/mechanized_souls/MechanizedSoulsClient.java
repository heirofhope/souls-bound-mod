package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.entity.client.KnightRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class MechanizedSoulsClient implements ClientModInitializer {


	@Override
	public void onInitializeClient(ModContainer mod) {

		EntityRendererRegistry.register(ModEntities.KNIGHT, KnightRenderer::new);
		//DO NOT FUCKING TOUCH THIS IF YOU ARENT 100% SURE YOU ARE NOT GONNA FUCK EVRYTHING UP
		// AND LEAVE IT TO HOUR TO SORT OUT IN THE MIDDLE OF THE NIGHT, Please.
		// -Hour
	}
}
