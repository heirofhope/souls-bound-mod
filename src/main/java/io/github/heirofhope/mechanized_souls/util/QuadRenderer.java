package io.github.heirofhope.mechanized_souls.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.Tessellator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;

public class CustomRenderer {

	public static void register() {
		// Register the event for rendering after the translucent layer
		WorldRenderEvents.AFTER_TRANSLUCENT.register(context -> {
			renderGrayQuad(context.matrixStack(), context.camera().getPos());
		});
	}

	private static void renderGrayQuad(MatrixStack matrices, Vec3d cameraPos) {
		// Quad position and size
		float x = 100.0f;
		float y = 100.0f;
		float z = 100.0f;
		float size = 5.0f;

		// Vertices of the quad (two triangles)
		float[][] vertices = {
			{x - size, y, z - size},
			{x + size, y, z - size},
			{x + size, y, z + size},
			{x - size, y, z + size},
		};

		// Transform the matrix stack relative to the camera
		matrices.push();
		matrices.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

		// Enable rendering features
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.disableTexture();

		// Use a VertexConsumerProvider
		VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
		VertexConsumer vertexConsumer = immediate.getBuffer(RenderLayer.getTranslucent());

		// Draw the quad using two triangles
		drawTriangle(vertexConsumer, matrices.peek().getPositionMatrix(), vertices[0], vertices[1], vertices[2]);
		drawTriangle(vertexConsumer, matrices.peek().getPositionMatrix(), vertices[0], vertices[2], vertices[3]);

		// Finalize rendering
		immediate.draw();

		// Restore rendering states
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
		matrices.pop();
	}

	private static void drawTriangle(VertexConsumer vertexConsumer, Matrix4f positionMatrix, float[] v1, float[] v2, float[] v3) {
		vertexConsumer.vertex(positionMatrix, v1[0], v1[1], v1[2])
			.color(128, 128, 128, 255) // Gray color
			.next();
		vertexConsumer.vertex(positionMatrix, v2[0], v2[1], v2[2])
			.color(128, 128, 128, 255) // Gray color
			.next();
		vertexConsumer.vertex(positionMatrix, v3[0], v3[1], v3[2])
			.color(128, 128, 128, 255) // Gray color
			.next();
	}
}
