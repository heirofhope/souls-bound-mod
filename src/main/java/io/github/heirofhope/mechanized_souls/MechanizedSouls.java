package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.Entity.ModEntities;
import io.github.heirofhope.mechanized_souls.Entity.custom.KnightEntity;
import io.github.heirofhope.mechanized_souls.item.ModItems;
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
        LOGGER.info("Hello The Mod Is Working {}! Run Some Test!", mod.metadata().name());
		ModItems.RegisterModItems();

		FabricDefaultAttributeRegistry.register(ModEntities.KNIGHT, KnightEntity.createKnightAttributes());
    }
}














//modding sucks don't do it

//Aristu- "wish I didn't know about modding T-T"
