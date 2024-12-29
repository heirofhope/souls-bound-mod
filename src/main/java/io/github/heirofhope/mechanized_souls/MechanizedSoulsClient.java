package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.entity.client.KnightRenderer;
import io.github.heirofhope.mechanized_souls.particle.ModParticles;
import io.github.heirofhope.mechanized_souls.particle.custom.ScytheChainParticle;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class MechanizedSoulsClient implements ClientModInitializer {


	@Override
	public void onInitializeClient(ModContainer mod) {

		ParticleFactoryRegistry.getInstance().register(ModParticles.SCYTHE_CHAIN_PARTICLE_1, ScytheChainParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.SCYTHE_CHAIN_PARTICLE_2, ScytheChainParticle.Factory::new);



		EntityRendererRegistry.register(ModEntities.KNIGHT, KnightRenderer::new);
		//DO NOT [censored] TOUCH THIS IF YOU AREN'T 100% SURE YOU ARE NOT GOING TO [censored] EVRYTHING UP
		// AND LEAVE IT TO HOUR TO SORT OUT IN THE MIDDLE OF THE NIGHT, Please.
		// -Hour
	}
}
