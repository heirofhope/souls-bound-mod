package io.github.heirofhope.mechanized_souls.entity.client;


import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.heirofhope.mechanized_souls.entity.custom.KnightEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class KnightModel<T extends KnightEntity> extends SinglePartEntityModel<T> {
	private final ModelPart Knight;
	private final ModelPart Head;
	public KnightModel(ModelPart root) {
		this.Knight = root.getChild("Knight");
		this.Head = this.Knight.getChild("Head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Knight = modelPartData.addChild("Knight", ModelPartBuilder.create().uv(54, 37).cuboid(3.0F, -20.0F, -1.0F, 5.0F, 14.0F, 5.0F, new Dilation(0.0F))
		.uv(38, 0).cuboid(3.0F, -21.0F, -2.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.0F))
		.uv(40, 56).cuboid(-15.0F, -21.0F, -2.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.0F))
		.uv(20, 56).cuboid(-14.0F, -20.0F, -1.0F, 5.0F, 14.0F, 5.0F, new Dilation(0.0F))
		.uv(34, 37).cuboid(-9.0F, -4.0F, -1.0F, 5.0F, 14.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 38).cuboid(-2.0F, -4.0F, -1.0F, 5.0F, 14.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-9.0F, -21.0F, -2.0F, 12.0F, 10.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 17).cuboid(-9.0F, -20.0F, -1.0F, 12.0F, 16.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 14.0F, 0.0F));

		ModelPartData Head = Knight.addChild("Head", ModelPartBuilder.create().uv(64, 0).cuboid(-6.0F, -28.0F, -1.0F, 6.0F, 7.0F, 5.0F, new Dilation(0.0F))
		.uv(34, 17).cuboid(-9.0F, -24.0F, -2.0F, 12.0F, 3.0F, 7.0F, new Dilation(0.0F))
		.uv(20, 38).cuboid(0.0F, -27.0F, -1.0F, 2.0F, 6.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 57).cuboid(-8.0F, -27.0F, -1.0F, 2.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Helmet_r1 = Head.addChild("Helmet_r1", ModelPartBuilder.create().uv(34, 27).cuboid(-6.0F, -3.0F, -2.0F, 10.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, -26.0F, 0.0F, -0.1745F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(KnightEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		Knight.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return Knight;
	}
}
