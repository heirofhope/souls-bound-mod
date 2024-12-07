package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.effect.ModEffects;
import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.entity.custom.KnightEntity;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import io.github.heirofhope.mechanized_souls.util.BeforeDeathHandler;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MechanizedSouls implements ModInitializer {
	public static final String MOD_ID = "mechanized_souls";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



    @Override
    public void onInitialize(ModContainer mod) {

<<<<<<< HEAD
	//	ModConfiguredFeatures.registerConfiguredFeature();
=======
		//	ModConfiguredFeatures.registerConfiguredFeature();
		LOGGER.info("Hello Quilt world from {}! Stay fresh!", mod.metadata().name());

		//Particle registration
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(MOD_ID, "soul_link_particle"), SOUL_LINK_PARTICLE);




		//Classes registrations
		ScytheChainLauncher.register();
>>>>>>> parent of e9b198a (Merge branch 'main' into ParticleScytheStringTest)
		ModItems.RegisterModItems();
		FabricDefaultAttributeRegistry.register(ModEntities.KNIGHT, KnightEntity.createKnightAttributes());
<<<<<<< HEAD

=======
		ScytheStringRenderer.register();
>>>>>>> parent of e9b198a (Merge branch 'main' into ParticleScytheStringTest)
		ModEffects.registerEffects();
		BeforeDeathHandler.register();

    }
}
