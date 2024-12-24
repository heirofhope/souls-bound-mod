package io.github.heirofhope.mechanized_souls.util.scytheChain;

import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScytheChainLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger("philomagia");

	// Cooldown tracker for players
	private static final Map<UUID, Integer> SHOOT_COOLDOWN = new HashMap<>();
	private static final int COOLDOWN_DURATION_TICKS = 30; // 1 second in ticks (20 ticks per second)

	public static void register() {
		//=======[ Main code ]=========================================

		// Tick event listener to handle cooldown decrement
		ServerTickEvents.START_SERVER_TICK.register(server -> {
			// Iterate over cooldowns and decrement remaining ticks
			SHOOT_COOLDOWN.entrySet().removeIf(entry -> {
				int newCooldown = entry.getValue() - 1;
				if (newCooldown <= 0) {
					return true; // Remove the entry if cooldown expires
				}
				SHOOT_COOLDOWN.put(entry.getKey(), newCooldown);
				return false;
			});
		});

		// Right-click event (UseItemCallback expects TypedActionResult<ItemStack>)
		UseItemCallback.EVENT.register((player, world, hand) -> {
			UUID playerUUID = player.getUuid();

			// Check if the player is on cooldown
			if (SHOOT_COOLDOWN.containsKey(playerUUID)) {
				LOGGER.info(player.getName().getString() + " tried to shoot but is on cooldown.");
				return TypedActionResult.fail(player.getStackInHand(hand)); // Fail action if on cooldown
			}

			// Add the player to the cooldown tracker
			SHOOT_COOLDOWN.put(playerUUID, COOLDOWN_DURATION_TICKS);
			if (player.getStackInHand(hand).getItem() == ModItems.SOUL_SCYTHE) {

				double shootSpeed = 1.3;

				Vec3d aim = player.getRotationVec(1.0F);

				ScytheChainMain.addParticle(
					new Vec3d(player.getX() + aim.getX(), player.getY() + 1.5 + aim.getY(), player.getZ() + aim.getZ()), // Position
					player.getUuid(),
					aim.multiply(shootSpeed), // Speed based on player's direction, doubled
					35 // Lifetime in ticks
				);

				// Clear existing chains for the player
				for (int i = ScytheChainMain.CHAIN_OWNERS.size() - 1; i >= 0; i--) {
					if (ScytheChainMain.CHAIN_OWNERS.get(i).equals(player.getUuid())) {
						ScytheChainMain.CHAIN_OWNERS.remove(i);
						ScytheChainMain.CHAIN_LIFETIME.remove(i);
						ScytheChainMain.CHAINED_ENTITYS.remove(i);
					}
				}
			}
			return TypedActionResult.pass(player.getStackInHand(hand)); // Correct return type
		});

	}
}
