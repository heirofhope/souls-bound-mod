package io.github.heirofhope.mechanized_souls.entity.client;


import io.github.heirofhope.mechanized_souls.entity.custom.KnightEntity;
import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KnightRenderer extends MobEntityRenderer<KnightEntity,KnightModel<KnightEntity>> {
	private static final Identifier TEXTURE = new Identifier(MechanizedSouls.MOD_ID, "textures/entity/knight.png");


	public KnightRenderer(EntityRendererFactory.Context context) {
		super(context, new KnightModel<>(context.getPart(ModModelLayers.KNIGHT)), .9f);
	}

	@Override
	public Identifier getTexture(KnightEntity entity) {
		return TEXTURE;
	}

	@Override
	public void render(KnightEntity mobEntity, float f, float g, MatrixStack matrixStack,
					   VertexConsumerProvider vertexConsumerProvider, int i) {
		super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}
}
