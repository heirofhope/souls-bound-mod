package io.github.heirofhope.mechanized_souls.mixin;

import io.github.heirofhope.mechanized_souls.item.custom.HalberdItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
	@Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
	private static void SoulsBound$getArmPoseDR(AbstractClientPlayerEntity player, Hand hand,
											  CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.getItem() instanceof HalberdItem) {
			if (!player.isUsingItem()) {
				cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
			}
		}
	}
}
