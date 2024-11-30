package io.github.heirofhope.mechanized_souls.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.math.BlockPos;

public class ChainLock extends StatusEffect {

	public ChainLock(StatusEffectType type, int color) {
		super(type, color);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (!entity.getWorld().isClient) {

			double Xpos = entity.getX();
			double Ypos = entity.getY();
			double Zpos = entity.getZ();
			BlockPos TargetPos = new BlockPos(100, 100, 100);
			double Xcorrection = (Math.abs(TargetPos.getX() - Xpos) - 10) * ( (TargetPos.getX() - Xpos) / Math.abs(TargetPos.getX() - Xpos));
			double Ycorrection = TargetPos.getY() - Ypos;
			double Zcorrection = TargetPos.getZ() - Zpos;

			entity.teleport(Xpos + Xcorrection,Ypos,Zpos);
			//entity.teleport(Xpos + Xcorrection,Ypos + Ycorrection,Zpos + Zcorrection);

		//	entity.setVelocity(Xcorrection,Ycorrection,Zcorrection);
		}
		super.applyUpdateEffect(entity, amplifier);
	}
}
