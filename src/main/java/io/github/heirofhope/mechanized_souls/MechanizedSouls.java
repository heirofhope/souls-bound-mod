package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.effect.ModEffects;
import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.entity.custom.KnightEntity;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import io.github.heirofhope.mechanized_souls.util.scytheChain.ScytheChainLauncher;
import io.github.heirofhope.mechanized_souls.util.scytheChain.ScytheChainMain;
import io.github.heirofhope.mechanized_souls.util.scytheChain.ScytheStringRenderer;
import io.github.heirofhope.mechanized_souls.util.BeforeDeathHandler;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MechanizedSouls implements ModInitializer {
	public static final String MOD_ID = "mechanized_souls";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final DefaultParticleType SOUL_LINK_PARTICLE = FabricParticleTypes.simple();


	@Override
    public void onInitialize(ModContainer mod) {


		//	ModConfiguredFeatures.registerConfiguredFeature();
		LOGGER.info("Hello Quilt world from {}! Stay fresh!", mod.metadata().name());

		//Particle registration
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(MOD_ID, "soul_link_particle"), SOUL_LINK_PARTICLE);




		//Classes registrations
		ScytheChainLauncher.register();

	//	ModConfiguredFeatures.registerConfiguredFeature();

		ModItems.RegisterModItems();
		ScytheChainMain.register();
		FabricDefaultAttributeRegistry.register(ModEntities.KNIGHT, KnightEntity.createKnightAttributes());

		ScytheStringRenderer.register();


		ModEffects.registerEffects();
		BeforeDeathHandler.register();

    }
}
