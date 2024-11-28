package io.github.heirofhope.mechanized_souls.entity.client;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.entity.custom.KnightEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KnightModel extends AnimatedGeoModel<KnightEntity> {

	@Override
	public Identifier getModelResource(KnightEntity object) {
		return new Identifier(MechanizedSouls.MOD_ID,"geo/fer_golem.geo.json");
	}

	@Override
	public Identifier getTextureResource(KnightEntity object) {
		return new Identifier(MechanizedSouls.MOD_ID,"textures/entity/knight/fer_golem.png");
	}

	@Override
	public Identifier getAnimationResource(KnightEntity animatable) {
		return new Identifier(MechanizedSouls.MOD_ID,"animations/fer_golem.animation.json");
	}
}
