package io.github.heirofhope.mechanized_souls.mixin;

import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemRenderer.class)
public interface ItemRendererAccessor {
	// Accessor for the 'models' field in ItemRenderer (adjust field name if necessary)
	@Accessor("models")
	ItemModels mccourse$getModels();
}
