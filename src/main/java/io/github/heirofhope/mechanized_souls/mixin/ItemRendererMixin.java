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

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
	@ModifyVariable(
		method = "renderItem",
		at = @At(value = "HEAD"),
		argsOnly = true
	)
	public BakedModel useHandHeldModel(BakedModel value, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		// Check if the item is the Ruby Staff and not in GUI mode
		if (stack.isOf(ModItems.BOOK_CYPHER) && renderMode != ModelTransformation.Mode.GUI) {
			// Replace the model with a custom model
			return MinecraftClient.getInstance()
				.getBakedModelManager()
				.getModel(new ModelIdentifier("mechanized_souls:book_cypher_held", "inventory"));
		}
		return value;
	}
}
