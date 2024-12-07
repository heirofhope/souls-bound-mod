package io.github.heirofhope.mechanized_souls.mixin;

import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

	@ModifyVariable(
		method = "renderItem",
		at = @At(value = "HEAD"),
		argsOnly = true
	)
	public BakedModel useHandHeldModel(BakedModel value, ItemStack stack, ModelTransformation.Mode renderMode,
									   boolean leftHanded, MatrixStack matrices, VertexConsumerProvider
											   vertexConsumers, int light, int overlay) {
		if (stack.isOf(ModItems.book_cypher) && renderMode != ModelTransformation.Mode.GUI) {
			return MinecraftClient.getInstance()
				.getBakedModelManager()
				.getModel(new ModelIdentifier("mechanized_souls:book_cypher_held", "inventory"));
		}
		if (stack.isOf(ModItems.DAWN_CLEAVER) && renderMode != ModelTransformation.Mode.GUI) {
			return MinecraftClient.getInstance()
				.getBakedModelManager()
				.getModel(new ModelIdentifier("mechanized_souls:dawn_cleaver_held", "inventory"));
		}
		if (stack.isOf(ModItems.ROCK_SWORD) && renderMode != ModelTransformation.Mode.GUI) {
			return MinecraftClient.getInstance()
				.getBakedModelManager()
				.getModel(new ModelIdentifier("mechanized_souls:rock_sword_held", "inventory"));
		}
		if (stack.isOf(ModItems.HALBERD) && renderMode != ModelTransformation.Mode.GUI) {
			return MinecraftClient.getInstance()
				.getBakedModelManager()
				.getModel(new ModelIdentifier("mechanized_souls:halberd_held", "inventory"));
		}
		if (stack.isOf(ModItems.SOUL_SCYTHE) && renderMode != ModelTransformation.Mode.GUI) {
			return MinecraftClient.getInstance()
				.getBakedModelManager()
				.getModel(new ModelIdentifier("mechanized_souls:soul_scythe_held", "inventory"));
		}
		if (stack.isOf(ModItems.ECLIPSE_CLAYMORE) && renderMode != ModelTransformation.Mode.GUI) {
			return MinecraftClient.getInstance()
				.getBakedModelManager()
				.getModel(new ModelIdentifier("mechanized_souls:eclipse_claymore_held", "inventory"));
		}
		return value;
	}
}
