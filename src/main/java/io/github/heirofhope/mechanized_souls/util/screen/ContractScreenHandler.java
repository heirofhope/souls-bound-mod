package io.github.heirofhope.mechanized_souls.util.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ContractScreenHandler extends ScreenHandler {
	public static final ScreenHandlerType<ContractScreenHandler> TYPE = Registry.register(
		Registry.SCREEN_HANDLER,
		new Identifier("eternity", "contract"),
		new ScreenHandlerType<>(ContractScreenHandler::new)
	);

	public ContractScreenHandler(int syncId, PlayerInventory inventory) {
		super(TYPE, syncId);
	}

	@Override
	public ItemStack quickTransfer(PlayerEntity player, int fromIndex) {
		return null;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}
