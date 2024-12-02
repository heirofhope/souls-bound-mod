package io.github.heirofhope.mechanized_souls.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
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
				} else {
					user.sendMessage(Text.of("One or both players are not online!"), false);
				}
			}
		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}





