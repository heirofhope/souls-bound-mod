package io.github.heirofhope.mechanized_souls.sounds;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
	public static SoundEvent GuitarHit =registerSoundEvent("guitar_hit");

	private static SoundEvent registerSoundEvent(String name) {
		Identifier id = new Identifier(MechanizedSouls.MOD_ID, name);
		return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}
}
