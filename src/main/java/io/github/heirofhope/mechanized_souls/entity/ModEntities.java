package io.github.heirofhope.mechanized_souls.entity;

import io.github.heirofhope.mechanized_souls.entity.custom.KnightEntity;
import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class ModEntities {
	public static final EntityType<KnightEntity> KNIGHT = Registry.register(Registry.REGISTRIES.ENTITY_TYPE,
		new Identifier(MechanizedSouls.MOD_ID, "knight"),
		FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, KnightEntity::new)
			.dimensions(EntityDimensions.fixed(1.5f,2.25f)).build());

	public static class ProjectileTutorialMod implements ModInitializer {
		public static final String ModID = "projectiletutorial"; // This is just so we can refer to our ModID easier.



		/**
		 * Runs the mod initializer.
		 *
		 * @param mod the mod which is initialized
		 */
		@Override
		public void onInitialize(ModContainer mod) {

		}
	}

	}
