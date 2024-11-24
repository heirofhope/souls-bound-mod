package io.github.heirofhope.mechanized_souls.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class HalberdItem extends SwordItem {
	public HalberdItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.canDisableShield();
		return super.postHit(stack, target, attacker);
	}
}
