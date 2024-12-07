package io.github.heirofhope.mechanized_souls.mixin;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {

	// Shadow the 'addModel' method to call it in our mixin
	@Shadow
	protected abstract void addModel(ModelIdentifier modelId);

	// Inject code after the third invocation of 'addModel' during initialization.
	@Inject(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V",
			ordinal = 3,
			shift = At.Shift.AFTER
		)
	)
	public void addHandHeldModel(CallbackInfo ci) {
		// Register the custom handheld model.
		this.addModel(new ModelIdentifier(MechanizedSouls.MOD_ID, "book_cypher_held", "inventory"));
		this.addModel(new ModelIdentifier(MechanizedSouls.MOD_ID, "dawn_cleaver_held", "inventory"));
		this.addModel(new ModelIdentifier(MechanizedSouls.MOD_ID, "halberd_held", "inventory"));
		this.addModel(new ModelIdentifier(MechanizedSouls.MOD_ID, "soul_scythe_held", "inventory"));
		this.addModel(new ModelIdentifier(MechanizedSouls.MOD_ID, "eclipse_claymore_held", "inventory"));
		}
}
