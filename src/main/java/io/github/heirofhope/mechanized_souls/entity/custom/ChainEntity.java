package io.github.heirofhope.mechanized_souls.entity.custom;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.item.custom.ChainItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
;

public class ChainEntity extends ThrownItemEntity {
	public ChainEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}

	public ChainEntity(World world, LivingEntity owner) {
		super(mechanized_souls.ChainEntityType, owner, world);
	}

	public ChainEntity(World world, double x, double y, double z) {
		super(mechanized_souls.ChainEntityType, x, y, z, world);
	}


	@Override
	protected Item getDefaultItem() {
		return mechanized_souls.ChainItem;
	}

	@Environment(EnvType.CLIENT)
	private ParticleEffect getParticleParameters() { // Not entirely sure, but probably has do to with the snowball's particles. (OPTIONAL)
		ItemStack itemStack = this.getItem();
		return (ParticleEffect) (itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
	}

	@Environment(EnvType.CLIENT)
	public void handleStatus(byte status) { // Also not entirely sure, but probably also has to do with the particles. This method (as well as the previous one) are optional, so if you don't understand, don't include this one.
		if (status == 3) {
			ParticleEffect particleEffect = this.getParticleParameters();

			for (int i = 0; i < 8; ++i) {
				this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)
		int i = entity instanceof BlazeEntity ? 3 : 0; // sets i to 3 if the Entity instance is an instance of BlazeEntity
		entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float) i); // deals damage

		if (entity instanceof LivingEntity livingEntity) { // checks if entity is an instance of LivingEntity (meaning it is not a boat or minecart)
			livingEntity.playSound(SoundEvents.AMBIENT_CAVE, 2F, 1F); // plays a sound for the entity hit only
		}
	}

	@Override
	protected void onBlockCollision(BlockState state) { // called on collision with a block
		super.onBlockCollision(state);
		if (!this.world.isClient) { // checks if the world is client
			this.world.sendEntityStatus(this, (byte) 3); // particle?
			this.kill(); // kills the projectile
		}
	}


	public void setVelocity(PlayerEntity user, float pitch, float yaw, float v, float v1, float v2) {
	}
}



