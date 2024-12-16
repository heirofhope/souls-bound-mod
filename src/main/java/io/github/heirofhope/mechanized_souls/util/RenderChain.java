package io.github.heirofhope.mechanized_souls.util;

import io.github.heirofhope.mechanized_souls.particle.ModParticles;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class RenderChain {
	public static void render (ServerWorld world, Vec3d chainStartPos, Vec3d endPos) {
	//=====================[CODE TO ACTUALLY CHAIN THE ENTITY]=====================//
		//...
	double Xpos = chainStartPos.getX() + 0.5;
	double Ypos = chainStartPos.getY() + 0.5;
	double Zpos = chainStartPos.getZ() + 0.5;

	// Define target position
	Vec3d targetPos = new Vec3d(endPos.x, endPos.y, endPos.z);
	double targetX = targetPos.getX() + 0.5; // Center of the block
	double targetY = targetPos.getY() + 0.5;
	double targetZ = targetPos.getZ() + 0.5;

	// Calculate distance
	double deltaX = targetX - Xpos;
	double deltaY = targetY - Ypos;
	double deltaZ = targetZ - Zpos;

	double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
					if(distance ==0)
	{
		return;
	}
	//=====================[CODE TO MAKE THE VISUALS]=====================//
	int ChainSteps = (int) Math.ceil(distance / 0.6) * 40;
	Vec3d ChainStepSize = new Vec3d(deltaX / ChainSteps, deltaY / ChainSteps, deltaZ / ChainSteps);

	double ropeLength = 6.5; // The total length of the rope

					for(
	int i = 0;
	i<ChainSteps;i++)

	{


		double n = i * 0.025;
		Vec3d localOffset = new Vec3d(
			0.25,
			Math.abs(Math.round(n) - n) + Math.round(n) * 0.6,
			Math.abs(n + 0.25 - Math.round(0.25 + n))
		);

		Vec3d yAxle = ChainStepSize.normalize();
		Vec3d xAxle = new Vec3d(yAxle.x, 0, yAxle.z).normalize().rotateY(90);
		Vec3d zAxle = rotateAroundAxis(xAxle, yAxle, 90);

		Vec3d globalParticlePos = (targetPos
			.subtract(yAxle.multiply(localOffset.y))
			.add(xAxle.multiply(localOffset.getX()))
			.add(zAxle.multiply(localOffset.getZ()))
		);


		world.spawnParticles(
			ModParticles.SCYTHE_CHAIN_PARTICLE_1,
			globalParticlePos.x,
			globalParticlePos.y,
			globalParticlePos.z,
			1,
			0, 0, 0,
			0
		);

		globalParticlePos = (targetPos
			.subtract(yAxle.multiply(localOffset.y + 0.3))
			.add(xAxle.multiply(localOffset.getZ()))
			.add(zAxle.multiply(localOffset.getX()))
		);


		world.spawnParticles(
			ModParticles.SCYTHE_CHAIN_PARTICLE_2,
			globalParticlePos.x,
			globalParticlePos.y,
			globalParticlePos.z,
			1,
			0, 0, 0,
			0
		);
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


