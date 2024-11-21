package io.github.heirofhope.mechanized_souls.mixin;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
	@Shadow
	private void renderBakedItemQuads(MatrixStack matrices, VertexConsumer vertices, List<BakedQuad> quads, ItemStack stack, int light, int overlay) {
	}

	@Unique
	private static final Object BOOK_CYPHER = renderBakedItemQuads();

	@Unique
	private static Object renderBakedItemQuads() {
		return null;
	}

	@ModifyVariable(
		method = "renderItem",
		at = @At(value = "HEAD"),
		argsOnly = true
	)
	public BakedModel useHandHeldModel(BakedModel value, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		// Check if the item is the Ruby Staff and not in GUI mode
		if (stack.isOf(ModItems.book_cypher) && renderMode != ModelTransformation.Mode.GUI) {
			// Replace the model with a custom model
			return MinecraftClient.getInstance()
				.getBakedModelManager()
				.getModel(new ModelIdentifier("mechanized_souls:book_cypher_held", "inventory"));
		}
		return value;
	}
}
