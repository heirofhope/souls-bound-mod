package io.github.heirofhope.mechanized_souls;

import io.github.heirofhope.mechanized_souls.item.ModItems;
import io.github.heirofhope.mechanized_souls.util.screen.ContractScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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
		Registry.register(Registry.SCREEN_HANDLER, new Identifier("eternity", "contract"), ContractScreenHandler.TYPE);
    }
}



//      Don't Look At icon.png, Worst Mistake Of My Life.
