package io.github.heirofhope.mechanized_souls.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;

public class DashEnchantment extends Enchantment {
	public DashEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot... slotTypes) {
		super(weight, type, slotTypes);


}
	@Override
	public int getMaxLevel() {
		return 1;
	}
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
			float yaw = user.getYaw();
			float pitch = user.getPitch();
			Vec3d direction = Vec3d.fromPolar(pitch, yaw).normalize().multiply(3.0);
			user.addVelocity(direction.x, direction.y, direction.z);
			if (user.isOnGround()) {
				user.move(MovementType.SELF, new Vec3d(0.0, 1.2, 0.0));
			}
		}
	}








