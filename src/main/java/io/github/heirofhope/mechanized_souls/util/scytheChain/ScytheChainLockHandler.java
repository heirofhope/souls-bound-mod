package io.github.heirofhope.mechanized_souls.util.scytheChain;

import net.minecraft.entity.Entity;
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

			LOGGER.info("Hmmmmmm okkk....");
			List<Entity> entitiesAtPosition = world.getEntitiesByClass(Entity.class, new Box(playerPos.subtract(50, 50, 50), playerPos.add(50,50,50)), entity -> entity != null && !entity.isSpectator());
			for (Entity entity : entitiesAtPosition) {
				if (entity.getUuid().equals(chainedEntity)) {

					//chain configurations
					double ChainLenght = 5;
					double MaxPullSpeed = 1;

					//...
					double Xpos = entity.getX();
					double Ypos = entity.getY();
					double Zpos = entity.getZ();

					// Define target position
					BlockPos targetPos = new BlockPos(playerPos.x, playerPos.y, playerPos.z);
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
					double correctionFactor = Math.max(MaxPullSpeed, 0.3 * (distance - ChainLenght));
					double xCorrection = Math.cos(horizontalAngle) * correctionFactor;
					double yCorrection = Math.sin(verticalAngle) * correctionFactor;
					double zCorrection = Math.sin(horizontalAngle) * correctionFactor;

					// Teleport entity with corrections
					if (distance > ChainLenght) {
						entity.setVelocity(xCorrection, yCorrection, zCorrection);
					}






				}
			}
		}
	}
