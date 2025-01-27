package io.github.heirofhope.mechanized_souls.mixin;

import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class PlayerEntityMixin {
	@Inject(
		method = "swingHand",
		at = @At("HEAD"),
		cancellable = true
	)
	private void swingHand(Hand hand, CallbackInfo info) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		if (player.getStackInHand(hand).getItem() == ModItems.GUITAR) {
			info.cancel();
		}
	}

}


