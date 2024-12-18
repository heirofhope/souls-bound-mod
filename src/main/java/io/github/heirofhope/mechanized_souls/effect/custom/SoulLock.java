package io.github.heirofhope.mechanized_souls.effect.custom;

import io.github.heirofhope.mechanized_souls.particle.ModParticles;
import io.github.heirofhope.mechanized_souls.util.AzuraParticleRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoulLock extends StatusEffect {
	private static final Logger LOGGER = LoggerFactory.getLogger("mechanized_souls_scythe");
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

			int remainingDuration = entity.getStatusEffect(this).getDuration();


			//in here can i get the effect remaining duration?

			double x = 0.1 * Math.round(entity.getX() * 10);
			double y = 0.1 * Math.round(entity.getY() * 10);
			double z = 0.1 * Math.round(entity.getZ() * 10);

					entity.teleport(x,y,z);

			entity.setVelocity(0,0,0);




			//hehe
			if (remainingDuration < 5980) {
//=================[ static rendering shenanigans ] =====================
				AzuraParticleRenderer.renderCircle(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(0, 1.4, 0)),
					new Vec3d(0, 1, 0),
					0.6,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2
				);

				AzuraParticleRenderer.renderCircle(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(0, 1, 0)),
					new Vec3d(0, 1, 0),
					0.6,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2
				);


				AzuraParticleRenderer.renderChain(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(1.5, -1, 0)),
					entity.getPos().add(new Vec3d(0.1, 1.3, 0)),
					ModParticles.SCYTHE_CHAIN_PARTICLE_1,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2

				);

				AzuraParticleRenderer.renderChain(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(-1.5, -1, 0)),
					entity.getPos().add(new Vec3d(-0.1, 1.3, 0)),
					ModParticles.SCYTHE_CHAIN_PARTICLE_1,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2

				);


				AzuraParticleRenderer.renderChain(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(0, -1, 1.5)),
					entity.getPos().add(new Vec3d(0, 1.3, 0.1)),
					ModParticles.SCYTHE_CHAIN_PARTICLE_1,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2

				);


				AzuraParticleRenderer.renderChain(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(0, -1, -1.5)),
					entity.getPos().add(new Vec3d(0, 1.3, -0.1)),
					ModParticles.SCYTHE_CHAIN_PARTICLE_1,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2

				);
			} else {
				//=================[ RAISING COOL THINGIE ANIMATION SHENANIGANS ]=====================
			double animationProgress = (double) (6015 - remainingDuration) / 35;
				LOGGER.info("Remaining Duration: " + animationProgress);


				AzuraParticleRenderer.renderCircle(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(0, 1.4 * animationProgress, 0)),
					new Vec3d(0, 1, 0),
					1.5 + (-0.9 * animationProgress),
					ModParticles.SCYTHE_CHAIN_PARTICLE_2
				);

				AzuraParticleRenderer.renderCircle(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(0, (1.4 * animationProgress) - 0.4, 0)),
					new Vec3d(0, 1, 0),
					1.5 + (-0.9 * animationProgress),
					ModParticles.SCYTHE_CHAIN_PARTICLE_2
				);

				AzuraParticleRenderer.renderChain(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(0, -1, -1.5)),
					entity.getPos().add(new Vec3d(0, -1, -1.5).
						add(new Vec3d(0, 2.3, 1.4).multiply(animationProgress)
						)),
					ModParticles.SCYTHE_CHAIN_PARTICLE_1,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2
				);

				AzuraParticleRenderer.renderChain(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(0, -1, 1.5)),
					entity.getPos().add(new Vec3d(0, -1, 1.5).
						add(new Vec3d(0, 2.3, -1.4).multiply(animationProgress)
						)),
					ModParticles.SCYTHE_CHAIN_PARTICLE_1,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2
				);

				AzuraParticleRenderer.renderChain(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(1.5, -1, 0)),
					entity.getPos().add(new Vec3d(1.5, -1, 0).
						add(new Vec3d(-1.4, 2.3, 0).multiply(animationProgress)
						)),
					ModParticles.SCYTHE_CHAIN_PARTICLE_1,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2
				);

				AzuraParticleRenderer.renderChain(
					(ServerWorld) entity.getWorld(),
					entity.getPos().add(new Vec3d(-1.5, -1, 0)),
					entity.getPos().add(new Vec3d(-1.5, -1, 0).
						add(new Vec3d(1.4, 2.3, 0).multiply(animationProgress)
						)),
					ModParticles.SCYTHE_CHAIN_PARTICLE_1,
					ModParticles.SCYTHE_CHAIN_PARTICLE_2
				);





			}

		}
		super.applyUpdateEffect(entity, amplifier);
	}
}
