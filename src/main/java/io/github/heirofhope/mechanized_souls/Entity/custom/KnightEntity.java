package io.github.heirofhope.mechanized_souls.Entity.custom;


import io.github.heirofhope.mechanized_souls.Entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KnightEntity extends AnimalEntity {
	public KnightEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
	}


	public static DefaultAttributeContainer.Builder createKnightAttributes() {
		return MobEntity.createMobAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 80)
			.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 40)
			.add(EntityAttributes.GENERIC_ARMOR, 40)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .1f)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15)
		    .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 20);
	}


	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return ModEntities.KNIGHT.create(world);
	}
}
