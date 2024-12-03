package io.github.heirofhope.mechanized_souls.entity.custom;

import io.github.heirofhope.mechanized_souls.entity.ModEntities;
import io.github.heirofhope.mechanized_souls.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
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
import net.minecraft.nbt.NbtCompound;
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

	private AnimationFactory factory = GeckoLibUtil.createFactory(this);
	private boolean isAnimating = true;

	protected static final TrackedData<Boolean> DORMANT = DataTracker.registerData(KnightEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public static final TrackedData<Optional<BlockPos>> DORMANT_POS = DataTracker.registerData(KnightEntity.class, TrackedDataHandlerRegistry.OPTIONAL_BLOCK_POS);

	public static final TrackedData<Integer> ATTACK_STATE = DataTracker.registerData(KnightEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public static final TrackedData<Integer> ACTION_STATE = DataTracker.registerData(KnightEntity.class, TrackedDataHandlerRegistry.INTEGER);

	public static final ActionResult RESULT = null;

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


	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_ANVIL_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_ANVIL_DESTROY;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState state) {
		this.playSound(SoundEvents.ENTITY_IRON_GOLEM_STEP, 0.15f, 1.0f);
	}


	/* TAMEABLE ENTITY */
	private static final TrackedData<Boolean> SITTING =
		DataTracker.registerData(KnightEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		if(player.getStackInHand(hand).isEmpty() && player.getUuid().equals(this.getOwnerUuid())) {
			if(player.isSneaking()) {
				if(!player.getAbilities().creativeMode)
					player.setStackInHand(hand, ModItems.KNIGHT_SPAWNEGG.getDefaultStack());
				this.remove(RemovalReason.DISCARDED);
				return ActionResult.SUCCESS;
			} else {
				this.cycleActionState(player);
			}
		}
		return super.interactMob(player, hand);
	}

	private void cycleActionState(PlayerEntity player) {
		if(getActionState() == 0) {
			setActionState(2);
			player.sendMessage(Text.translatable("amogus", world.getRegistryKey().getValue().getPath()).setStyle(Style.EMPTY.withColor(Formatting.DARK_RED).withObfuscated(true).withFont(new Identifier("minecraft", "default"))), true);
		} else if(getActionState() == 2) {
			setActionState(1);
			player.sendMessage(Text.translatable("info.aylyth.mould_activate", world.getRegistryKey().getValue().getPath()).setStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
		} else if(getActionState() == 1) {
			setActionState(0);
			player.sendMessage(Text.translatable("info.aylyth.mould_deactivate", world.getRegistryKey().getValue().getPath()).setStyle(Style.EMPTY.withColor(Formatting.DARK_GRAY)), true);
		}


	}
	public int getActionState() {
		return this.dataTracker.get(ACTION_STATE);
	}
	public void setActionState(int i) {
		this.dataTracker.set(ACTION_STATE, i);
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
			getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(.24f);
			getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(15);
			getAttributeInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(20);
		} else {
			getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(80);
			getAttributeInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS).setBaseValue(40);
			getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(40);
			getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(.24f);
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
		animationData.addAnimationController(new AnimationController(this,"controller",
			0,this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}


