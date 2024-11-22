package io.github.heirofhope.mechanized_souls.Entity.custom;


import io.github.heirofhope.mechanized_souls.Entity.ModEntities;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class KnightEntity extends TameableEntity {

	public static final ActionResult RESULT = null;

	public KnightEntity(EntityType<? extends TameableEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new AttackWithOwnerGoal(this));
		this.goalSelector.add(2,new WanderAroundGoal(this,2,30));

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


	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		Item item = itemStack.getItem();
		if (this.world.isClient) {
			boolean bl = this.isOwner(player) || this.isTamed() || itemStack.isOf(ModItems.soul_cookie) && !this.isTamed();
			return bl ? ActionResult.CONSUME : ActionResult.PASS;


		}else{

			if (itemStack.isOf(ModItems.soul_cookie)) {
				if (!player.getAbilities().creativeMode) {
					itemStack.decrement(1);
				}


					this.setOwner(player);
					this.navigation.stop();
					this.setTarget(null);
					this.setSitting(true);
					this.world.sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);


				return ActionResult.SUCCESS;
			}

		}
		return super.interactMob(player, hand);
	}
}


