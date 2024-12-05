package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.effect.ModEffects;
import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.entity.custom.KnightEntity;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import io.github.heirofhope.mechanized_souls.util.BeforeDeathHandler;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.Item;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MechanizedSouls implements ModInitializer {
	public static final String MOD_ID = "mechanized_souls";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
    public void onInitialize(ModContainer mod) {

	//	ModConfiguredFeatures.registerConfiguredFeature();
        LOGGER.info("Hello Quilt world from {}! Stay fresh!", mod.metadata().name());
		ModItems.RegisterModItems();
		FabricDefaultAttributeRegistry.register(ModEntities.KNIGHT, KnightEntity.createKnightAttributes());

		ModEffects.registerEffects();
		BeforeDeathHandler.register();

    }
}
