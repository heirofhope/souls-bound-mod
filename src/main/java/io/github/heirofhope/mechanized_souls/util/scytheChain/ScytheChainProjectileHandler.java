package io.github.heirofhope.mechanized_souls.util.scytheChain;

import io.github.heirofhope.mechanized_souls.particle.ModParticles;
import io.github.heirofhope.mechanized_souls.util.particleRenderer.AzuraParticleRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.UUID;

public class ScytheChainProjectileHandler {

	public static void render(ServerWorld world, List<Vec3d> particlePositions, List<Vec3d> projectileSpeeds, List<Integer> projectileLifetime, List<UUID> projectileOwners) {

		// Chain behavior configssss (pweasee if you can you not fix all ortografy mistakes? They're cute...)
		double ChainProjectileHitboxSize = 0.7; // hmmmmm The actual size is doubled so.... Yeaaaahhhh
		double ChainGravity = -0.00; // I like -0.005....

		// Iterate in reverse to avoid index issues during removal yipeeee that worked somehow? mewoooo
		for (int i = particlePositions.size() - 1; i >= 0; i--) {

			// Get values of the iteration in question
			Vec3d position = particlePositions.get(i);
			Vec3d speed = projectileSpeeds.get(i);
			int lifetime = projectileLifetime.get(i);
			UUID owner = projectileOwners.get(i);

			// Apply gravity and decrement lifetime (If we add any more projectile movement logic add hereeee)
			lifetime--;
			speed = speed.add(0, ChainGravity, 0); // Add gravity to the y-component

			// Check if the projectile's lifetime has expired, if so delete it
			if (lifetime <= 0) {
				particlePositions.remove(i);
				projectileSpeeds.remove(i);
				projectileLifetime.remove(i);
				projectileOwners.remove(i);
				continue;
			}

			// If it still has not expired, do everything else (〃＾▽＾〃)
			int steps = 10; // Number of interpolation steps
			Vec3d stepVector = speed.multiply(3.0 / steps); // Divide speed into smaller steps to interpolate!! Yayyyyyyyyy
			Vec3d interpolatedPosition = position;

			boolean hasHitEntity = false;
			for (int n = 0; n < steps; n++) {
				interpolatedPosition = interpolatedPosition.add(stepVector);

				// Detect if collided with entities (´･ω･`) 🔍
				List<Entity> entitiesAtPosition = world.getEntitiesByClass(Entity.class,
					new Box(interpolatedPosition.subtract(ChainProjectileHitboxSize, ChainProjectileHitboxSize, ChainProjectileHitboxSize),
						interpolatedPosition.add(ChainProjectileHitboxSize, ChainProjectileHitboxSize, ChainProjectileHitboxSize)),
					entity -> entity != null && !entity.isSpectator());

				for (Entity entity : entitiesAtPosition) {
					if (entity.getBoundingBox().contains(interpolatedPosition)) {

						ScytheChainMain.addChain(owner,entity.getUuid(),400);
						//add some endrod particles here


						for (int b = 0; b < 10; b++) {
							double offsetX = (entity.getWorld().random.nextDouble() - 0.5) * 2.0;
							double offsetY = entity.getWorld().random.nextDouble() * 1.5;
							double offsetZ = (entity.getWorld().random.nextDouble() - 0.5) * 2.0;
							world.spawnParticles(
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


						hasHitEntity = true;
						break; // We hit something, stop checking further
					}
				}

				if (hasHitEntity) {
					break; // Exit interpolation loop if we hit something
				}
			}

			// Handle chain particle visuals only if no collision occurred
			if (!hasHitEntity) {
				position = position.add(speed);

				// Look for the owner of the projectile to chain to it ヾ(⌐■_■)ノ♪
				List<Entity> lookingForOwner = world.getEntitiesByClass(Entity.class,
					new Box(position.subtract(100, 100, 100), position.add(100, 100, 100)),
					entity -> entity != null && !entity.isSpectator());

				for (Entity entity : lookingForOwner) {
					if (entity.getUuid().equals(owner)) {
						AzuraParticleRenderer.renderChain(world,entity.getPos().add(new Vec3d(0,1,0)),position.add(new Vec3d(0,1,0)), ModParticles.SCYTHE_CHAIN_PARTICLE_1,ModParticles.SCYTHE_CHAIN_PARTICLE_2);
					}
				}

				// Update values
				particlePositions.set(i, position);
				projectileSpeeds.set(i, speed);
				projectileLifetime.set(i, lifetime);
			} else {
				// If we hit an entity, safely remove projectile from all lists! ✂️
				particlePositions.remove(i);
				projectileSpeeds.remove(i);
				projectileLifetime.remove(i);
				projectileOwners.remove(i);
			}
		}
	}
}
