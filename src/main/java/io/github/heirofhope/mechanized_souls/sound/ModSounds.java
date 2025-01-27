package io.github.heirofhope.mechanized_souls.sound;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

	public static SoundEvent GUITAR_HIT = registerSoundEvent("guitar_hit_sound");

	private static SoundEvent registerSoundEvent(String name) {
		Identifier id = new Identifier(MechanizedSouls.MOD_ID, name);
		return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}

	public static void registerSounds() {
		MechanizedSouls.LOGGER.info("Registering Mod Sounds for " + MechanizedSouls.MOD_ID);
	}
}
