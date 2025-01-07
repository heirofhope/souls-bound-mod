package io.github.heirofhope.mechanized_souls.mixin.client;

import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> {
	@Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
	private void liby$setAngles(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
		if (livingEntity.getMainHandStack().getItem().equals(ModItems.GUITAR) ) {
			BipedEntityModel<? extends LivingEntity> model = (BipedEntityModel<? extends LivingEntity>) (Object) this;

			model.rightArm.pitch = -(float)Math.toRadians(19.5108d); // X
			model.rightArm.yaw = -(float)Math.toRadians(3.841d); // Y
			model.rightArm.roll = (float)Math.toRadians(-14.5108d); // Z

			model.leftArm.pitch = -(float)Math.toRadians(2.4927d);
			model.leftArm.yaw = -(float)Math.toRadians(0.449d);
			model.leftArm.roll = (float)Math.toRadians(-127.538d);
		}
	}
}


