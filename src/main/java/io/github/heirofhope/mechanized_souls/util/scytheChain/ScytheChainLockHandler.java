package io.github.heirofhope.mechanized_souls.util.scytheChain;

import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class ScytheChainLockHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger("mechanized_souls_scythe");
	public static void SoulLockTick(ServerWorld world, UUID chainedEntity, Vec3d playerPos) {


			List<Entity> entitiesAtPosition = world.getEntitiesByClass(Entity.class, new Box(playerPos.subtract(50, 50, 50), playerPos.add(50,50,50)), entity -> entity != null && !entity.isSpectator());
			for (Entity entity : entitiesAtPosition) {
				if (entity.getUuid().equals(chainedEntity)) {

					//=====================[CODE TO ACTUALLY CHAIN THE ENTITY]=====================//
					//chain configurations
					double ChainLenght = 6.5;
					double MaxPullSpeed = 0.7;

					//...
					double Xpos = entity.getX();
					double Ypos = entity.getY();
					double Zpos = entity.getZ();

					// Define target position
					Vec3d targetPos = new Vec3d(playerPos.x, playerPos.y, playerPos.z);
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
					double correctionFactor = Math.max(MaxPullSpeed, 0.2 * (distance - ChainLenght));
					double xCorrection = Math.cos(horizontalAngle) * correctionFactor;
					double yCorrection = Math.sin(verticalAngle) * correctionFactor;
					double zCorrection = Math.sin(horizontalAngle) * correctionFactor;

					// push entity back SMOOTHLY
					if (distance > ChainLenght) {
						entity.setVelocity(xCorrection + (entity.getVelocity().getX() * 0.4), yCorrection + (entity.getVelocity().getY() * 0.4 ), (zCorrection + entity.getVelocity().getZ() * 0.4 ));
					}

					//=====================[CODE TO MAKE THE VISUALS]=====================//
					int ChainSteps = 90;


					Vec3d ChainStepSize = new Vec3d(deltaX / ChainSteps, deltaY / ChainSteps, deltaZ / ChainSteps);

					double ropeLength = 6.5; // The total length of the rope
					double segmentLength = ropeLength / ChainSteps; // Length of each chain segment

					for (int i = 0; i < ChainSteps; i++) {
						double yOffset = Math.abs( i - 45 )/45;

						world.spawnParticles(
							ParticleTypes.ELECTRIC_SPARK,
							targetPos.getX() - i * ChainStepSize.getX(),
							yOffset + 1.5 + targetPos.getY() - i * ChainStepSize.getY(),
							targetPos.getZ() - i * ChainStepSize.getZ(),
							1,
							0, 0, 0,
							0
						);
					}

				}
			}
		}
	}
