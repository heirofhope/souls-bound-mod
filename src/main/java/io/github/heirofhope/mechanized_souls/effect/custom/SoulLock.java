package io.github.heirofhope.mechanized_souls.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class SoulLock extends StatusEffect {

	public SoulLock(StatusEffectType type, int color) {
		super(type, color);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (!entity.getWorld().isClient) {

			double x = entity.getX();
			double y = entity.getY();
			double z = entity.getZ();

			entity.teleport(x,y,z);

			entity.setVelocity(0,0,0);
		}
		super.applyUpdateEffect(entity, amplifier);
	}
}
