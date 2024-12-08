package io.github.heirofhope.mechanized_souls.util.scytheChain;

//===============[ Imports ]=================
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
		double ChainProjectileHitboxSize = 0.5; // hmmmmm The actual size is doubled so.... Yeaaaahhhh
		double ChainGravity = -0.007; //I like -0.005....


		// Iterate in reverse to avoid index issues during removal yipeeee that worked somehow?
		for (int i = particlePositions.size() - 1; i >= 0; i--) {

			// Get values of the iteration in question
			Vec3d position = particlePositions.get(i);
			Vec3d speed = projectileSpeeds.get(i);
			int lifetime = projectileLifetime.get(i);

			// Apply gravity and decrement lifetime (If we add any more projectile movement logic add hereeee)
			lifetime--;
			speed = speed.add(0, ChainGravity, 0); // Add gravity to the y-component

			// Check if the projectile's lifetime has expired, if so delete it
			if (lifetime <= 0) {
				// Remove expired projectile from all lists
				particlePositions.remove(i);
				projectileSpeeds.remove(i);
				projectileLifetime.remove(i);
				projectileOwners.remove(i);
			} else {

				//If it still has not expired do everthing else (〃＾▽＾〃)

				// To prevent the projectile clipping trough things we'll interpolate its position. Increase interpolation steps if launch speed is increased a lot
				int steps = 10; // Number of interpolation steps

				Vec3d stepVector = speed.multiply(3.0 / steps); // Divide speed into smaller steps to interpolate!! Yayyyyyyyyy

				Vec3d interpolatedPosition = position;

				Boolean HasHitEntity = false;
				for (int n = 0; n < steps; n++) {
					interpolatedPosition = interpolatedPosition.add(stepVector);


					List<Entity> entitiesAtPosition = world.getEntitiesByClass(Entity.class, new Box(interpolatedPosition.subtract(ChainProjectileHitboxSize, ChainProjectileHitboxSize, ChainProjectileHitboxSize), interpolatedPosition.add(ChainProjectileHitboxSize, ChainProjectileHitboxSize, ChainProjectileHitboxSize)), entity -> entity != null && !entity.isSpectator());
					for (Entity entity : entitiesAtPosition) {

						//detect if collided with entitys
						if (entity.getBoundingBox().contains(interpolatedPosition)) {
							ScytheChainMain.CHAINED_ENTITYS =entity.getUuid();
							HasHitEntity = true;
						}
					}

					//generate particles to make it pwettyyy
					world.spawnParticles(
						ParticleTypes.END_ROD,
						interpolatedPosition.x, interpolatedPosition.y, interpolatedPosition.z,
						2,        // Number of particles
						0.02, 0.02, 0.02, // Spread/offset
						0.001       // Speed multiplier for particles
					);
				}

				if (HasHitEntity){

					particlePositions.remove(i);
					projectileSpeeds.remove(i);
					projectileLifetime.remove(i);
					projectileOwners.remove(i);

				} else {

					// Update position with full speed (after interpolation)
					position = position.add(speed);

					// Save updated values
					particlePositions.set(i, position);
					projectileLifetime.set(i, lifetime);
					projectileSpeeds.set(i, speed);


				}
			}
		}
	}
}

