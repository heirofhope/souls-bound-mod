package io.github.heirofhope.mechanized_souls.world.feature;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
	public static final List<OreFeatureConfig.Target> NETHER_TARGET_ORES = List.of(OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER, Blocks.ANCIENT_DEBRIS.getDefaultState()));
	public static final DynamicRegistryManager.RegistryEntry<PlacedFeature> ANCIENT_DEBRIS_PLACED =
		NetherPlacedFeatures.register("ancient_debris_placed", ModConfiguredFeatures.NETHER_TARGET_ORES, modifiersWithCount)


	public static void registerConfiguredFeature(){
		MechanizedSouls.LOGGER.debug("Registeriong the ModConfiguredFeatures for " + MechanizedSouls.MOD_ID);
	}
}
