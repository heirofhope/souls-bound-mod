package io.github.heirofhope.mechanized_souls.item.custom;

import io.github.heirofhope.mechanized_souls.util.screen.ContractScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WritableBookItem;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ContractItem extends WritableBookItem {
	public ContractItem(Settings settings) {
		super(settings);
	}
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient) {
			user.openHandledScreen(new SimpleNamedScreenHandlerFactory(
				(syncId, inv, player) -> new ContractScreenHandler(syncId, inv),
				Text.translatable("screen.mechanized_souls.contract")
			));
		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
