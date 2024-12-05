package io.github.heirofhope.mechanized_souls.item.custom;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.entity.custom.ChainEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import static io.github.heirofhope.mechanized_souls.entity.custom.mechanized_souls.ModID;

public class ChainItem extends Item {

	public ChainItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);

		// Play sound effect
		world.playSound(null, user.getX(), user.getY(), user.getZ(),
			SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 1F);

		if (!world.isClient) {
			// Create and configure the ChainEntity
			ChainEntity chainEntity = new ChainEntity(world, user);
			chainEntity.setItem(itemStack);
			chainEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0F);
			world.spawnEntity(chainEntity);
		}

		// Increment player's stat and decrement item stack if not in creative mode
		user.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!user.getAbilities().creativeMode) {
			itemStack.decrement(1);
		}

		return TypedActionResult.success(itemStack, world.isClient());
	}

	public static final Item ChainItem = new ChainItem(new Item.Settings().maxCount(1));

	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier(ModID, "Chain_Item"), ChainItem);
	}
}







