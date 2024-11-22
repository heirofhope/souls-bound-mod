package io.github.heirofhope.mechanized_souls.Entity.client;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
	public static final EntityModelLayer KNIGHT =
		new EntityModelLayer(new Identifier(MechanizedSouls.MOD_ID, "knight"), "main");
}
