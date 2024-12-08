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

			double x = 0.1 * Math.round(entity.getX() * 10);
			double y = 0.1 * Math.round(entity.getY() * 10);
			double z = 0.1 * Math.round(entity.getZ() * 10);

					entity.teleport(x,y,z);

			entity.setVelocity(0,0,0);
		}
		super.applyUpdateEffect(entity, amplifier);
	}
}
