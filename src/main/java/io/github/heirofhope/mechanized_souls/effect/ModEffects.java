package io.github.heirofhope.mechanized_souls.effect;


import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.effect.custom.ChainLock;
import io.github.heirofhope.mechanized_souls.effect.custom.SoulLock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModEffects {

	public static StatusEffect SOULLOCK;
	public static StatusEffect CHAINLOCK;
	public static StatusEffect CONFUSION;

	public static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
		return Registry.register(Registry.STATUS_EFFECT, new Identifier(MechanizedSouls.MOD_ID, name), effect);
	}

	public static void registerEffects() {
		SOULLOCK = registerStatusEffect("soullock", new SoulLock(StatusEffectType.HARMFUL, 312462));
		CHAINLOCK = registerStatusEffect("chainlock", new ChainLock(StatusEffectType.HARMFUL, 312462));
		CONFUSION = registerStatusEffect("confusion", new ChainLock(StatusEffectType.HARMFUL, 312462));
	}
}
