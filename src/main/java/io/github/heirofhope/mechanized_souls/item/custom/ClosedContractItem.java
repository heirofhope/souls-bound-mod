package io.github.heirofhope.mechanized_souls.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

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
}

