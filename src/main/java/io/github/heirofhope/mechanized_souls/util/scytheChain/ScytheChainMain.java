package io.github.heirofhope.mechanized_souls.util.scytheChain;

import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//This is the main code responsible to run the scythe.
//    (\_/)
//    ( ^‿^)
//    /  />♥

public class ScytheChainMain {
	private static final Logger LOGGER = LoggerFactory.getLogger("mechanized_souls_scythe");

	// Setting all arrays responsible to track the projectiles and chained entitys
	public static final List<Vec3d> PROJECTILE_POSITIONS = new ArrayList<>();
	public static final List<Vec3d> PROJECTILE_SPEEDS = new ArrayList<>();
	public static final List<UUID> PROJECTILE_OWNERS = new ArrayList<>();
	public static final List<Integer> PROJECTILE_LIFETIME = new ArrayList<>();
	public static  UUID CHAINED_ENTITYS = null;

	public static void register() {
		LOGGER.info("FormulaeProjectileProcessor registered.");




		ServerTickEvents.START_SERVER_TICK.register(server -> {

			for (World world : server.getWorlds()) {
				if (world instanceof ServerWorld serverWorld) {

					ScytheChainProjectileHandler.render(serverWorld, PROJECTILE_POSITIONS, PROJECTILE_SPEEDS, PROJECTILE_LIFETIME, PROJECTILE_OWNERS);
				}

				if (world instanceof ServerWorld serverWorld) {
					for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
						if (player.getMainHandStack().getItem() == ModItems.SOUL_SCYTHE)

							//Call the MATH
							ScytheChainLockHandler.SoulLockTick(serverWorld, CHAINED_ENTITYS, player.getPos());


					}

				}


			}
		});
	}

	public static void addParticle(Vec3d position, UUID owner, Vec3d speed, int lifetime) {
		PROJECTILE_POSITIONS.add(position);
		PROJECTILE_OWNERS.add(owner);
		PROJECTILE_SPEEDS.add(speed);
		PROJECTILE_LIFETIME.add(lifetime);

	}
}
