package io.github.heirofhope.mechanized_souls.item.custom;

import io.github.heirofhope.mechanized_souls.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class BookCypher extends Item {

	public BookCypher(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		if (entity.hasStatusEffect(ModEffects.SOULLOCK)){
			entity.removeStatusEffect(ModEffects.SOULLOCK);
		}
		return super.useOnEntity(stack, user, entity, hand);
	}
}
