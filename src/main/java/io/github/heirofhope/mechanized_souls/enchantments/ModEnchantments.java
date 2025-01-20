package io.github.heirofhope.mechanized_souls.enchantments;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEnchantments {
		public static Enchantment dash = register("dash",
			new DashEnchantment(Enchantment.Rarity.UNCOMMON,
				EnchantmentTarget.WEAPON, EquipmentSlot.MAINHAND));



	private static Enchantment register(String name, Enchantment enchantment) {
		return Registry.register(Registry.ENCHANTMENT, new Identifier(MechanizedSouls.MOD_ID, name), enchantment);
	}

	public static void registerModEnchantments() {
		System.out.println("Registering Enchantments for " + MechanizedSouls.MOD_ID);
	}
}
