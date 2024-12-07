package io.github.heirofhope.mechanized_souls.effect;


import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.effect.custom.SoulLock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModEffects {

	public static StatusEffect SOULLOCK;

	public static StatusEffect registerStatusEffect(String name) {
		return Registry.register(Registry.STATUS_EFFECT, new Identifier(MechanizedSouls.MOD_ID, name),
			new SoulLock(StatusEffectType.HARMFUL, 312462));
	}

	public static void registerEffects() {
		SOULLOCK = registerStatusEffect("soullock");
	}

}
