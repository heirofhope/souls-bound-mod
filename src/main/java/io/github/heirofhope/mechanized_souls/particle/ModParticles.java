package io.github.heirofhope.mechanized_souls.particle;


import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {
	public static final DefaultParticleType SCYTHE_CHAIN_PARTICLE = FabricParticleTypes.simple();



	public static void registerParticles() {

		Registry.register(Registry.PARTICLE_TYPE, new Identifier(MechanizedSouls.MOD_ID, "scythe_chain_particle"),
			SCYTHE_CHAIN_PARTICLE);
	}
}
