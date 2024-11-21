package io.github.heirofhope.mechanized_souls.item.custom;

import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ContractItem extends Item {
	public ContractItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);

		if (!world.isClient) {
			// Create the signed contract item stack
			ItemStack signedContract = new ItemStack(ModItems.closed_contract);
			NbtCompound nbt = new NbtCompound();
			nbt.putString("SignedBy", player.getName().getString());
			signedContract.setNbt(nbt);

			// Replace the item in the player's hand
			player.setStackInHand(hand, signedContract);
		}

		return TypedActionResult.success(stack);
	}
}
