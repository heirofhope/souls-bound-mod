package io.github.heirofhope.mechanized_souls.item.custom;

import io.github.heirofhope.mechanized_souls.entity.custom.ChainEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static io.github.heirofhope.mechanized_souls.entity.custom.mechanized_souls.ModID;

public class SoulDrainItem extends AxeItem {

	public SoulDrainItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}
}



