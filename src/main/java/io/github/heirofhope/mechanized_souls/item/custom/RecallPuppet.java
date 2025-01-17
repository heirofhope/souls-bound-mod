package io.github.heirofhope.mechanized_souls.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class RecallPuppet extends Item {

	public RecallPuppet(Settings settings) {
		super(settings);
	}




	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient) { // Ensure the logic only runs on the server
			MinecraftServer server = user.getServer(); // Get the server instance
			if (server != null) {
				// Get players by name
				ServerPlayerEntity playerToTeleport = server.getPlayerManager().getPlayer("Aritsu_");
				ServerPlayerEntity targetPlayer = server.getPlayerManager().getPlayer("Foxracoon_");

				if (playerToTeleport != null && targetPlayer != null) {
					// Teleport the player
					playerToTeleport.teleport(
						targetPlayer.getWorld(),
						targetPlayer.getX(),
						targetPlayer.getY(),
						targetPlayer.getZ(),
						targetPlayer.getYaw(),
						targetPlayer.getPitch()
					);

					// Send a confirmation message to the user
					user.sendMessage(Text.of("Teleportation successful!"), false);
				} else {
					// Send an error message if either player is not online
					user.sendMessage(Text.of("One or both players are not online!"), false);
				}
			}
		}
		return TypedActionResult.success(user.getStackInHand(hand)); // Ensure the item doesn't disappear
	}
	}





