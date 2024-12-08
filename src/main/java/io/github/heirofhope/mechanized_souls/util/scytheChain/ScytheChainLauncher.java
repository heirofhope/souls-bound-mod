package io.github.heirofhope.mechanized_souls.util.scytheChain;

//===============[ Imports ]=================

import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ScytheChainLauncher {
	private static final Logger LOGGER = LoggerFactory.getLogger("philomagia");

	// Keep track of players holding diamonds
	private static final Set<ServerPlayerEntity> playersHoldingDiamond = new HashSet<>();

	public static void register() {
		//=======[ Main code ]=========================================
		LOGGER.info("yep here's a logger copy pasta");

		// Tick event listener
		ServerTickEvents.START_SERVER_TICK.register(server -> {
			//=======[ Tick event!! ]=========================================



		});

		// Right-click event (UseItemCallback expects TypedActionResult<ItemStack>)
		UseItemCallback.EVENT.register((player, world, hand) -> {
			LOGGER.info(player.getName().getString() + " right-clicked while holding: " + player.getStackInHand(hand).getItem());
			if (player.getStackInHand(hand).getItem()== ModItems.SOUL_SCYTHE){

				double shootSpeed = 0.8;

					Vec3d aim = player.getRotationVec(1.0F);

				ScytheChainMain.addParticle(
					new Vec3d(player.getX() + aim.getX(), player.getY() + 1.5 + aim.getY(), player.getZ() + aim.getZ()), // Position
					player.getUuid(),
					aim.multiply(shootSpeed), // Speed based on player's direction, doubled
					170 // Lifetime in ticks
				);



			}
			return TypedActionResult.pass(player.getStackInHand(hand)); // Correct return type
		});


		// Left-click event (AttackEntityCallback expects specific parameters)
		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (!world.isClient) {
				LOGGER.info(player.getName().getString() + " left-clicked on entity: " + entity.getName().getString());
			}
			return ActionResult.PASS; // Correct return type for AttackEntityCallback
		});

		// Player death event (Using AFTER_RESPAWN as ALLOW_DEATH is deprecated)
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			LOGGER.info(newPlayer.getName().getString() + " has respawned.");
		});

		// On player death (remove ALLOW_DEATH and use alternatives if necessary)
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			LOGGER.info(oldPlayer.getName().getString() + " has died and respawned.");
		});
	}
}
