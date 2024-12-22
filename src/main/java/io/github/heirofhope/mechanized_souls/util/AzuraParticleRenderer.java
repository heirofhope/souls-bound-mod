package io.github.heirofhope.mechanized_souls.util;

import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class AzuraParticleRenderer {

	// Chain renderer
	public static void renderChain(ServerWorld world, Vec3d chainStartPos, Vec3d endPos, DefaultParticleType Particle1, DefaultParticleType Particle2) {
		double Xpos = chainStartPos.getX() + 0.5;
		double Ypos = chainStartPos.getY() + 0.5;
		double Zpos = chainStartPos.getZ() + 0.5;

		Vec3d targetPos = new Vec3d(endPos.x, endPos.y, endPos.z);
		double targetX = targetPos.getX() + 0.5;
		double targetY = targetPos.getY() + 0.5;
		double targetZ = targetPos.getZ() + 0.5;

		double deltaX = targetX - Xpos;
		double deltaY = targetY - Ypos;
		double deltaZ = targetZ - Zpos;

		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
		if (distance == 0) {
			return;
		}

		int ChainSteps = (int) ((distance / 0.3) - 4) * 20;
		Vec3d ChainStepSize = new Vec3d(deltaX / ChainSteps, deltaY / ChainSteps, deltaZ / ChainSteps);

		for (int i = 0; i < ChainSteps; i++) {
			double n = i * 0.025;
			Vec3d localOffset = new Vec3d(
				0.25,
				Math.abs(Math.round(n) - n) + Math.round(n) * 0.6,
				Math.abs(n + 0.25 - Math.round(0.25 + n))
			);

			Vec3d yAxle = ChainStepSize.normalize();
			Vec3d xAxle = (yAxle.x == 0 && yAxle.z == 0) ? new Vec3d(1, 0, 0) : new Vec3d(yAxle.x, 0, yAxle.z).normalize().rotateY(90);
			Vec3d zAxle = rotateAroundAxis(xAxle, yAxle, 90);

			Vec3d globalParticlePos = targetPos
				.subtract(yAxle.multiply(localOffset.y))
				.add(xAxle.multiply(localOffset.getX()))
				.add(zAxle.multiply(localOffset.getZ()));

			// Spawn for all players in the world
			for (ServerPlayerEntity player : world.getPlayers()) {
				world.spawnParticles(player, Particle1, true, globalParticlePos.x, globalParticlePos.y, globalParticlePos.z, 1, 0, 0, 0, 0);
			}

			globalParticlePos = targetPos
				.subtract(yAxle.multiply(localOffset.y + 0.3))
				.add(xAxle.multiply(localOffset.getZ()))
				.add(zAxle.multiply(localOffset.getX()));

			// Spawn for all players in the world
			for (ServerPlayerEntity player : world.getPlayers()) {
				world.spawnParticles(player, Particle2, true, globalParticlePos.x, globalParticlePos.y, globalParticlePos.z, 1, 0, 0, 0, 0);
			}
		}
	}

	// Line renderer
	public static void renderLine(ServerWorld world, Vec3d chainStartPos, Vec3d endPos, DefaultParticleType Particle1) {
		double Xpos = chainStartPos.getX() + 0.5;
		double Ypos = chainStartPos.getY() + 0.5;
		double Zpos = chainStartPos.getZ() + 0.5;

		Vec3d targetPos = new Vec3d(endPos.x, endPos.y, endPos.z);
		double targetX = targetPos.getX() + 0.5;
		double targetY = targetPos.getY() + 0.5;
		double targetZ = targetPos.getZ() + 0.5;

		double deltaX = targetX - Xpos;
		double deltaY = targetY - Ypos;
		double deltaZ = targetZ - Zpos;

		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
		if (distance == 0) {
			return;
		}

		int lineSteps = (int) Math.ceil(distance) * 20;
		Vec3d lineStepSize = new Vec3d(deltaX / lineSteps, deltaY / lineSteps, deltaZ / lineSteps);

		for (int i = 0; i < lineSteps; i++) {
			double n = i * 0.05;
			Vec3d localOffset = new Vec3d(0, n, 0);

			Vec3d yAxle = lineStepSize.normalize();
			Vec3d xAxle = (yAxle.x == 0 && yAxle.z == 0) ? new Vec3d(1, 0, 0) : new Vec3d(yAxle.x, 0, yAxle.z).normalize().rotateY(90);
			Vec3d zAxle = rotateAroundAxis(xAxle, yAxle, 90);

			Vec3d globalParticlePos = targetPos
				.subtract(yAxle.multiply(localOffset.y))
				.add(xAxle.multiply(localOffset.getX()))
				.add(zAxle.multiply(localOffset.getZ()));

			// Spawn for all players in the world
			for (ServerPlayerEntity player : world.getPlayers()) {
				world.spawnParticles(player, Particle1, true, globalParticlePos.x, globalParticlePos.y, globalParticlePos.z, 1, 0, 0, 0, 0);
			}
		}
	}

	// Circle renderer
	public static void renderCircle(ServerWorld world, Vec3d center, Vec3d direction, double radius, DefaultParticleType Particle1) {
		int ChainSteps = 120;

		for (int i = 0; i < ChainSteps; i++) {
			Vec3d localOffset = new Vec3d(
				radius * Math.sin(Math.toRadians(3 * i)),
				0,
				radius * Math.cos(Math.toRadians(3 * i))
			);

			Vec3d yAxle = direction.normalize();
			Vec3d xAxle = (yAxle.x == 0 && yAxle.z == 0) ? new Vec3d(1, 0, 0) : new Vec3d(yAxle.x, 0, yAxle.z).normalize().rotateY(90);
			Vec3d zAxle = rotateAroundAxis(xAxle, yAxle, 90);

			Vec3d globalParticlePos = center
				.subtract(yAxle.multiply(localOffset.y))
				.add(xAxle.multiply(localOffset.getX()))
				.add(zAxle.multiply(localOffset.getZ()));

			// Spawn for all players in the world
			for (ServerPlayerEntity player : world.getPlayers()) {
				world.spawnParticles(player, Particle1, true, globalParticlePos.x, globalParticlePos.y, globalParticlePos.z, 1, 0, 0, 0, 0);
			}
		}
	}

	public static Vec3d rotateAroundAxis(Vec3d vector, Vec3d axis, double angleDegrees) {
		Vec3d normalizedAxis = axis.normalize();

		double cosTheta = Math.cos(Math.toRadians(angleDegrees));
		double sinTheta = Math.sin(Math.toRadians(angleDegrees));

		Vec3d parallelComponent = normalizedAxis.multiply(vector.dotProduct(normalizedAxis));
		Vec3d perpendicularComponent = vector.subtract(parallelComponent);
		Vec3d crossProductComponent = normalizedAxis.crossProduct(vector);

		return parallelComponent
			.add(perpendicularComponent.multiply(cosTheta))
			.add(crossProductComponent.multiply(sinTheta));
	}
}
