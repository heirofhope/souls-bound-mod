package io.github.heirofhope.mechanized_souls.item.custom;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.sounds.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.AxeItem;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;

public class GuitarItem extends AxeItem {

	public GuitarItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}
	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

		float pitch = 0.7f + (attacker.getRandom().nextFloat() * 0.6f);
		attacker.getWorld().playSound(null, attacker.getBlockPos(), ModSounds.GuitarHit, SoundCategory.PLAYERS, .125f, pitch);


		return super.postHit(stack, target, attacker);
	}
}







