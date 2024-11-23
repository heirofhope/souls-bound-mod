package io.github.heirofhope.mechanized_souls.Entity.custom;


import io.github.heirofhope.mechanized_souls.Entity.ModEntities;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.scoreboard.AbstractTeam;
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
		this.goalSelector.add(1,new SitGoal(this));
		this.goalSelector.add(2, new AttackWithOwnerGoal(this));
		this.goalSelector.add(3, new MeleeAttackGoal(this, 1.0, true));

	}


	public static DefaultAttributeContainer.Builder createKnightAttributes() {
		return TameableEntity.createMobAttributes()
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


	/* TAMEABLE ENTITY */
	private static final TrackedData<Boolean> SITTING =
		DataTracker.registerData(KnightEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getStackInHand(hand);
		Item item = itemstack.getItem();

		Item itemForTaming = ModItems.soul_cookie;

		if (item == itemForTaming && !isTamed()) {
			if (this.world.isClient()) {
				return ActionResult.CONSUME;
			} else {
				if (!player.getAbilities().creativeMode) {
					itemstack.decrement(1);
				}

				if (!this.world.isClient()) {
					super.setOwner(player);
					this.navigation.recalculatePath();
					this.setTarget(null);
					this.world.sendEntityStatus(this, (byte)7);
					setSit(true);
					this.world.sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
				}

				return ActionResult.SUCCESS;
			}
		}

		if(isTamed() && !this.world.isClient() && hand == Hand.MAIN_HAND) {
			setSit(!isSitting());
			return ActionResult.SUCCESS;
		}

		if (itemstack.getItem() == itemForTaming) {
			return ActionResult.PASS;
		}

		return super.interactMob(player, hand);
	}

	public void setSit(boolean sitting) {
		this.dataTracker.set(SITTING, sitting);
		super.setSitting(sitting);
	}

	public boolean isSitting() {
		return this.dataTracker.get(SITTING);
	}

	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
		if (tamed) {
			getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(80);
			getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(40);
			getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(40);
			getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(.1f);
			getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(15);
			getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(20);
		} else {
			getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(80);
			getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(40);
			getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(40);
			getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(.1f);
			getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(15);
			getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(20);
		}
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putBoolean("isSitting", this.dataTracker.get(SITTING));
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.dataTracker.set(SITTING, nbt.getBoolean("isSitting"));
	}

	@Override
	public AbstractTeam getScoreboardTeam() {
		return super.getScoreboardTeam();
	}

	public boolean canBeLeashedBy(PlayerEntity player) {
		return false;
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(SITTING, false);
	}
}


