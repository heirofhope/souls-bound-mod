package io.github.heirofhope.mechanized_souls.entity.custom;

import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Optional;

public class KnightEntity extends TameableEntity implements IAnimatable {

	private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
	private boolean isAnimating = true;

	private static final TrackedData<Boolean> SITTING = DataTracker.registerData(KnightEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public static final TrackedData<Integer> ACTION_STATE = DataTracker.registerData(KnightEntity.class, TrackedDataHandlerRegistry.INTEGER);

	public KnightEntity(EntityType<? extends TameableEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new SitGoal(this));
		this.goalSelector.add(2, new AttackWithOwnerGoal(this));
		this.goalSelector.add(3, new MeleeAttackGoal(this, 1.7, true));
	}

	// Setting attributes for the Knight entity
	public static DefaultAttributeContainer.Builder createKnightAttributes() {
		return TameableEntity.createMobAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 80)
			.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 40)
			.add(EntityAttributes.GENERIC_ARMOR, 40)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .1f)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15)
			.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 20);
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

	// Override the constructor to auto-tame after spawning
	@Override
	public void tick() {
		super.tick();

		// Auto-tame after entity is created
		if (!this.isTamed() && !world.isClient) {
			PlayerEntity closestPlayer = world.getClosestPlayer(this, 10); // Find the closest player within 10 blocks
			if (closestPlayer != null) {
				this.setOwner(closestPlayer); // Set player as the owner
				this.setTamed(true); // Tame the entity
			}
		}
	}

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		if (!world.isClient) {
			// Interactions for the owner
			if (this.isTamed() && player.getUuid().equals(this.getOwnerUuid())) {
				if (player.isSneaking()) {
					// Pick up entity as a spawn egg
					if (!player.getAbilities().creativeMode) {
						player.setStackInHand(hand, ModItems.KNIGHT_SPAWNEGG.getDefaultStack());
					}
					this.remove(RemovalReason.DISCARDED);
					return ActionResult.SUCCESS;
				} else {
					// Cycle action states
					this.cycleActionState(player);
					return ActionResult.SUCCESS;
				}
			}
		}
		return super.interactMob(player, hand);
	}

	private void cycleActionState(PlayerEntity player) {
		// Get the current action state
		int currentState = getActionState();

		// Toggle between 0 and 1
		int newState = (currentState == 0) ? 1 : 0;
		setActionState(newState); // Update the state

		// Send the appropriate message based on the new state
		if (newState == 0) {
			// State 0 is "activated"
			player.sendMessage(Text.translatable("message.knight.activated")
				.formatted(Formatting.AQUA), true);
		} else {
			// State 1 is "deactivated"
			player.sendMessage(Text.translatable("message.knight.deactivated")
				.formatted(Formatting.DARK_GRAY), true);
		}
	}

	public int getActionState() {
		return this.dataTracker.get(ACTION_STATE);
	}

	public void setActionState(int state) {
		this.dataTracker.set(ACTION_STATE, state);
	}

	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
		if (tamed) {
			this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(80);
			this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(40);
			this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(40);
			this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(.24f);
			this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(15);
			this.getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(20);
		}
	}

	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return ModEntities.KNIGHT.create(world);
	}

	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(SITTING, false);
		this.dataTracker.startTracking(ACTION_STATE, 0);
	}

	// Animation handling
	private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (this.isAnimating) {
			event.getController()
				.setAnimation(new AnimationBuilder().addAnimation("animation.fer_golem.idle_notafterthatgyatt", ILoopType.EDefaultLoopTypes.LOOP)
					.addAnimation("animation.fer_golem.walk", ILoopType.EDefaultLoopTypes.LOOP));
		} else {
			event.getController().clearAnimationCache();
			return PlayState.STOP;
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
