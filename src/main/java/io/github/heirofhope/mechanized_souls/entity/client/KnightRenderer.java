package io.github.heirofhope.mechanized_souls.entity.client;


import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.entity.custom.KnightEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class KnightRenderer extends GeoEntityRenderer<KnightEntity> {

	public KnightRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new KnightModel());
	}

	@Override
	public Identifier getTextureLocation(KnightEntity animatable) {
		return new Identifier(MechanizedSouls.MOD_ID,"textures/entity/knight/fer_golem.png");
	}
}
