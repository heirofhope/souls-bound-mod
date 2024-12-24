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

		this.velocityMultiplier = 0F; // Prevents velocity from affecting movement
		this.x = xCoord;
		this.y = yCoord;
		this.z = zCoord;
		this.scale = 0.08f;
		this.maxAge = 1;
		this.setSpriteForAge(spriteSet);

		this.velocityX = 0;
		this.velocityY = 0;
		this.velocityZ = 0; // Ensure no velocity
	}

	@Override
	public void tick() {
		super.tick();

		// Override velocity again in case it's modified externally
		this.velocityX = 0;
		this.velocityY = 0;
		this.velocityZ = 0;

		// Optionally, you can remove this.markDead() if it shouldn't disappear after one tick
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
