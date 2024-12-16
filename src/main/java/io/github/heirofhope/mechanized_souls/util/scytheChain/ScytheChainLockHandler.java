package io.github.heirofhope.mechanized_souls.util.scytheChain;

import io.github.heirofhope.mechanized_souls.particle.ModParticles;
import io.github.heirofhope.mechanized_souls.util.RenderChain;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class ScytheChainLockHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger("mechanized_souls_scythe");

	public static void SoulLockTick(ServerWorld world, UUID chainedEntity, Vec3d playerPos) {


		List<Entity> entitiesAtPosition = world.getEntitiesByClass(Entity.class, new Box(playerPos.subtract(50, 50, 50), playerPos.add(50, 50, 50)), entity -> entity != null && !entity.isSpectator());
		for (Entity entity : entitiesAtPosition) {
			if (entity.getUuid().equals(chainedEntity)) {

				//=====================[CODE TO ACTUALLY CHAIN THE ENTITY]=====================//
				//chain configurations
				double ChainLenght = 6.5;
				double MaxPullSpeed = 0.7;

				//...
				double Xpos = entity.getX() + 0.5;
				double Ypos = entity.getY() + 0.5;
				double Zpos = entity.getZ() + 0.5;

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
					entity.setVelocity(xCorrection + (entity.getVelocity().getX() * 0.4), yCorrection + (entity.getVelocity().getY() * 0.4), (zCorrection + entity.getVelocity().getZ() * 0.4));
				}

				//=====================[CODE TO MAKE THE VISUALS]=====================//
				RenderChain.render(world,entity.getPos().add(new Vec3d(0,1,0)),playerPos.add(new Vec3d(0,1,0)));

			}
		}
	}

	public static Vec3d rotateAroundAxis(Vec3d vector, Vec3d axis, double angleDegrees) {

		Vec3d normalizedAxis = axis.normalize();

		double cosTheta = Math.cos(Math.toRadians(angleDegrees));
		double sinTheta = Math.sin(Math.toRadians(angleDegrees));

		// (v · k)k
		Vec3d parallelComponent = normalizedAxis.multiply(vector.dotProduct(normalizedAxis));

		// v - (v · k)k
		Vec3d perpendicularComponent = vector.subtract(parallelComponent);

		// k × v
		Vec3d crossProductComponent = normalizedAxis.crossProduct(vector);

		// v_rot = parallelComponent + cosTheta * perpendicularComponent + sinTheta * crossProductComponent
		return parallelComponent
			.add(perpendicularComponent.multiply(cosTheta))
			.add(crossProductComponent.multiply(sinTheta));
	}
}




