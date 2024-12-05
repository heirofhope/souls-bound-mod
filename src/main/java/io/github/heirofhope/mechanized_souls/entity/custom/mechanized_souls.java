package io.github.heirofhope.mechanized_souls.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class mechanized_souls implements ModInitializer {
	public static final String ModID = "mechanized_souls"; // This is just so we can refer to our ModID easier.

	public static final EntityType<ChainEntity> PackedSnowballEntityType = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(ModID, "ChainEntity"),
		FabricEntityTypeBuilder.<ChainEntity>create(SpawnGroup.MISC, ChainEntity::new)
			.dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
			.trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
			.build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
	);
	public static EntityType<? extends ThrownItemEntity> ChainEntityType;
	public static Item ChainItem;


	@Override
	public void onInitialize(ModContainer mod) {

	}


	/**
	 * Runs the mod initializer.
	 *
	 * @param mod the mod which is initialized
	 */

}
