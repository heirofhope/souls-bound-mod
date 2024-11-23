package io.github.heirofhope.mechanized_souls.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;

public class BookCypher extends Item {

	public BookCypher(Settings settings) {
		super(settings);
	}

	@Override
	public boolean onClicked(ItemStack thisStack, ItemStack otherStack, Slot thisSlot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
		


		return super.onClicked(thisStack, otherStack, thisSlot, clickType, player, cursorStackReference);
	}
}
