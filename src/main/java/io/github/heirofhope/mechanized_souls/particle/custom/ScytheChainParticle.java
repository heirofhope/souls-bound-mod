package io.github.heirofhope.mechanized_souls.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class ScytheChainParticle extends SpriteBillboardParticle {
	protected ScytheChainParticle(ClientWorld level, double xCoord, double yCoord, double zCoord,
							  SpriteProvider spriteSet, double xd, double yd, double zd) {
		super(level, xCoord, yCoord, zCoord, xd, yd, zd);

		this.velocityMultiplier = 1F;
		this.x = xCoord;
		this.y = yCoord;
		this.z = zCoord;
		this.scale = 0.04f;
		this.maxAge = 1;
		this.setSpriteForAge(spriteSet);

//		this.red = 1f;
//		this.green = 1f;
//		this.blue = 1f;
	}

	@Override
	public void tick() {
		super.tick();
		this.markDead(); // Ensures the particle is removed
	}



	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Environment(EnvType.CLIENT)
	public static class Factory implements ParticleFactory<DefaultParticleType> {
		private final SpriteProvider sprites;

		public Factory(SpriteProvider spriteSet) {
			this.sprites = spriteSet;
		}

		public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
									   double dx, double dy, double dz) {
			return new ScytheChainParticle(level, x, y, z, this.sprites, 0, 0, 0);
		}
	}
}

