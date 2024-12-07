package io.github.heirofhope.mechanized_souls.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;


public class ScytheStringRenderer {
	// Particle position base (100, 100, 100)
	private static final BlockPos BASE_POSITION = new BlockPos(100, 100, 100);
	private static double X = 0.5; // X offset
	private static double Y = 0.5; // Y offset
	private static double Z = 0.5; // Z offset
	private static double DeltaTime = 0.5;
	private static double DragFrequencyMultiplier = 1;
	private static double DragSpeedMultiplier = 0.5;
	private static double DragAmplitude = 0.2;

	public static void register() {
		// Register a server tick callback to spawn particles
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerWorld world : server.getWorlds()) {
				if (!world.getPlayers().isEmpty()) {
					PlayerEntity player = world.getPlayers().get(0); // First player in the world
					double Xplayer = player.getX();
					double Yplayer = player.getY()+1.7;
					double Zplayer = player.getZ();

					spawnParticles(world, player, Xplayer, Yplayer, Zplayer);
				}
			}
		});
	}

	private static void spawnParticles(ServerWorld world, PlayerEntity player, double Xplayer, double Yplayer, double Zplayer) {
		// Send player coordinates to chat
		X = 0;
		Y = 0;
		Z = 0;
		DeltaTime = DeltaTime + 1 ;
		for (int i = 0; i < 60; i++) {
			world.spawnParticles(
					ParticleTypes.SOUL_FIRE_FLAME, // Changed particle type to blue fire
					BASE_POSITION.getX() + X, // X position with offset
					BASE_POSITION.getY() + Y + (Math.sin((DeltaTime * DragSpeedMultiplier) + (i / DragFrequencyMultiplier)) * DragAmplitude), // Y position with offset
					BASE_POSITION.getZ() + Z, // Z position with offset
					1, // Count of particles
					0.01, // X spread
					0.01, // Y spread
					0.01, // Z spread
					0.0  // Reduced speed for short lifetime
			);

			X = X - (BASE_POSITION.getX() - Xplayer) / 60;
			Y = Y - (BASE_POSITION.getY() - Yplayer) / 60;
			Z = Z - (BASE_POSITION.getZ() - Zplayer) / 60;
		}
	}
}

