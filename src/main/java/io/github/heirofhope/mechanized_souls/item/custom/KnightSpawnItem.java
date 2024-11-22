package io.github.heirofhope.mechanized_souls.item.custom;

import io.github.heirofhope.mechanized_souls.Entity.ModEntities;
import io.github.heirofhope.mechanized_souls.Entity.custom.KnightEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class KnightSpawnItem extends Item {

	public KnightSpawnItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		Direction direction = context.getSide();

		if (!world.isClient) {

			BlockPos spawnPos = pos.offset(direction);


			KnightEntity entity = ModEntities.KNIGHT.create(world);

			if (entity != null) {

				entity.refreshPositionAndAngles(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0, 0);


				world.spawnEntity(entity);
			}
		}


		context.getStack().decrement(1);

		return ActionResult.SUCCESS;
	}
}
