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
				// Get the player by name
				ServerPlayerEntity playerToTeleport = server.getPlayerManager().getPlayer("Aritsu_");

				if (playerToTeleport != null) {
					// Teleport the player to their current position
					playerToTeleport.teleport(
						playerToTeleport.getWorld(),
						playerToTeleport.getX(),
						playerToTeleport.getY(),
						playerToTeleport.getZ(),
						playerToTeleport.getYaw(),
						playerToTeleport.getPitch()
					);

					// Send a message only to the recalled player
					playerToTeleport.sendMessage(Text.of("Aritsu_ has been recalled!"), false);
				} else {
					// Send an error message if the player is not online
					user.sendMessage(Text.of("Aritsu_ is not online!"), false);
				}
			}
		}
		return TypedActionResult.success(user.getStackInHand(hand)); // Ensure the item doesn't disappear
	}
}




