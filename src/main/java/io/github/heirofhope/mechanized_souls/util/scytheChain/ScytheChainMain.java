package io.github.heirofhope.mechanized_souls.util.scytheChain;

import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//This is the main code responsible to run the scythe.
//    (\_/)
//    ( ^‿^)
//    /  />♥

public class ScytheChainMain {


	// Setting all arrays responsible to track the projectiles <B
	public static final List<Vec3d> PROJECTILE_POSITIONS = new ArrayList<>();
	public static final List<Vec3d> PROJECTILE_SPEEDS = new ArrayList<>();
	public static final List<UUID> PROJECTILE_OWNERS = new ArrayList<>();
	public static final List<Integer> PROJECTILE_LIFETIME = new ArrayList<>();
	// Setting all arrays responsible to track the chains :D
	public static final List <UUID> CHAIN_OWNERS = new ArrayList<>();
	public static final List <UUID> CHAINED_ENTITYS = new ArrayList<>();
	public static final List <Integer> CHAIN_LIFETIME = new ArrayList<>();

	public static void register() {

		ServerTickEvents.START_SERVER_TICK.register(server -> {

			for (World world : server.getWorlds()) {
				if (world instanceof ServerWorld serverWorld) {

					ScytheChainProjectileHandler.render(serverWorld, PROJECTILE_POSITIONS, PROJECTILE_SPEEDS, PROJECTILE_LIFETIME, PROJECTILE_OWNERS);
				}

				if (world instanceof ServerWorld serverWorld) {
					for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
						if (player.getMainHandStack().getItem() == ModItems.SOUL_SCYTHE) {

							for (int i = CHAIN_OWNERS.size() - 1; i >= 0; i--) {
								// Verify the chain exists before processing
								if (i < CHAINED_ENTITYS.size() && i < CHAIN_OWNERS.size() && CHAIN_LIFETIME.size() > i) {
									// Call the MATH
									if (player.getUuid().equals(CHAIN_OWNERS.get(i))) {
										ScytheChainLockHandler.SoulLockTick(serverWorld, CHAINED_ENTITYS.get(i), player.getPos());
									}
								}
							}
						}
					}

					for (int i = CHAIN_LIFETIME.size() - 1; i >= 0; i--) {
						// Safeguard against inconsistent list sizes
						if (i < CHAIN_LIFETIME.size() && i < CHAIN_OWNERS.size() && i < CHAINED_ENTITYS.size()) {
							CHAIN_LIFETIME.set(i, CHAIN_LIFETIME.get(i) - 1);
							if (CHAIN_LIFETIME.get(i) <= 0) {
								CHAIN_LIFETIME.remove(i);
								CHAIN_OWNERS.remove(i);
								CHAINED_ENTITYS.remove(i);
							}
						}
					}
				}
			}
		});
	}

	public static void addParticle(Vec3d position, UUID owner, Vec3d speed, int lifetime) {
		if (position != null && owner != null && speed != null && lifetime > 0) { // Prevent null and invalid lifetime values
			PROJECTILE_POSITIONS.add(position);
			PROJECTILE_OWNERS.add(owner);
			PROJECTILE_SPEEDS.add(speed);
			PROJECTILE_LIFETIME.add(lifetime);
		}
	}

	public static void addChain(UUID owner, UUID chainedEntity, int lifetime) {
		if (owner != null && chainedEntity != null && lifetime > 0) { // Validate inputs
			CHAIN_OWNERS.add(owner);
			CHAINED_ENTITYS.add(chainedEntity);
			CHAIN_LIFETIME.add(lifetime);
		}
	}
}
