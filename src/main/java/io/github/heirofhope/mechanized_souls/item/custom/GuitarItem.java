package io.github.heirofhope.mechanized_souls.item.custom;

import io.github.heirofhope.mechanized_souls.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
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
		attacker.getWorld().playSound(
			null,
			attacker.getBlockPos(),
			ModSounds.GUITAR_HIT,
			SoundCategory.PLAYERS,
			5f,
			pitch);

			//attacker.playSound(ModSounds.GUITAR_HIT, 1.0F, 1.0F);

			System.out.println("Guitar Solo Sound Effect");

		return super.postHit(stack, target, attacker);
	}
}







