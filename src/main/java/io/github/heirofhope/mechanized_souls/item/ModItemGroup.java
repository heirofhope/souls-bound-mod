package io.github.heirofhope.mechanized_souls.item;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
	public static final ItemGroup HEIR_TAB = FabricItemGroupBuilder.build(
		new Identifier(MechanizedSouls.MOD_ID, "heir_tab"), () ->new ItemStack(ModItems.FERROCIUM));
}
