package io.github.heirofhope.mechanized_souls.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ClosedContractItem extends WrittenBookItem {
	public ClosedContractItem(Settings settings) {
		super(settings);
	}

	@Override
	public Text getName(ItemStack stack) {
		NbtCompound nbt = stack.getNbt();
		if (nbt != null && nbt.contains("SignedBy")) {
			return Text.literal("Signed Contract - " + nbt.getString("SignedBy"));
		}
		return super.getName(stack);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient && user instanceof ServerPlayerEntity serverPlayer) {
			// Ban the player
			serverPlayer.networkHandler.disconnect(Text.literal("You Have Broken Your Contract."));

			// Remove the item from the player's hand
			ItemStack stackInHand = user.getStackInHand(hand);
			stackInHand.decrement(1);

			// Synchronize inventory
			serverPlayer.currentScreenHandler.sendContentUpdates();

			// Log the action
			String playerName = serverPlayer.getGameProfile().getName();
			System.out.println("Player " + playerName + " Was Banned For Breaking Their Contract.");
		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}



