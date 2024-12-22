package io.github.heirofhope.mechanized_souls.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class Confution extends StatusEffect {

	public Confution(StatusEffectType type, int color) {
	super(type, color);
}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (!entity.getWorld().isClient) {

			entity.setVelocity(-1,-1,-1);
		}
		super.applyUpdateEffect(entity, amplifier);
	}
}

