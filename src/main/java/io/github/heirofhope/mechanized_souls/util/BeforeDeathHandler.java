package io.github.heirofhope.mechanized_souls.util;

import io.github.heirofhope.mechanized_souls.effect.ModEffects;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class BeforeDeathHandler {

	public static void register() {
//run the code in all entity after life reaching 0
		ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {

			if (source.getAttacker() instanceof PlayerEntity player) {
				ItemStack weapon = player.getMainHandStack();
				//run if entity was killed by dawn cleaver
				if (weapon.getItem() == ModItems.DAWN_CLEAVER) {

					//increases entity health to prevent its death
					entity.setHealth(250.0F);

					//spawn endrod particles
					if (entity.getWorld() instanceof ServerWorld serverWorld) {
						for (int i = 0; i < 20; i++) {
							double offsetX = (entity.getWorld().random.nextDouble() - 0.5) * 2.0;
							double offsetY = entity.getWorld().random.nextDouble() * 1.5;
							double offsetZ = (entity.getWorld().random.nextDouble() - 0.5) * 2.0;
							serverWorld.spawnParticles(
								ParticleTypes.END_ROD,
								entity.getX(),
								entity.getY() + 0.5,
								entity.getZ(),
								10,
								offsetX,
								offsetY,
								offsetZ,
								0.1
							);
						}
					}
					//apply soulock effect
					entity.addStatusEffect(new StatusEffectInstance(ModEffects.SOULLOCK, 6000, 0, false, false));

				}
			}
		});
	}
}
