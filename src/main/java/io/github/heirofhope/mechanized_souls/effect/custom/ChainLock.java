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
			// Get entity's current position
			double Xpos = entity.getX();
			double Ypos = entity.getY();
			double Zpos = entity.getZ();

			// Define target position
			BlockPos targetPos = new BlockPos(100, 100, 100);
			double targetX = targetPos.getX() + 0.5; // Center of the block
			double targetY = targetPos.getY() + 0.5;
			double targetZ = targetPos.getZ() + 0.5;

			// Calculate distance
			double deltaX = targetX - Xpos;
			double deltaY = targetY - Ypos;
			double deltaZ = targetZ - Zpos;

			double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

			if (distance == 0) {
				return;
			}

			double horizontalAngle = Math.atan2(deltaZ, deltaX); // Rotation on XZ plane
			double verticalAngle = Math.asin(deltaY / distance); // Elevation angle

			double correctionFactor = distance - 10; // Limit movement to 10 units per tick
			double xCorrection = Math.cos(horizontalAngle) * correctionFactor;
			double yCorrection = Math.sin(verticalAngle) * correctionFactor;
			double zCorrection = Math.sin(horizontalAngle) * correctionFactor;

			// Teleport entity with corrections
			if (distance > 10) {
				entity.teleport(Xpos + xCorrection, Ypos + yCorrection, Zpos + zCorrection);
			}
		}
		super.applyUpdateEffect(entity, amplifier);
	}
}
