package io.github.heirofhope.mechanized_souls.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.World;

public class ChainPorjectileEntity extends ProjectileEntity {

	public ChainPorjectileEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initDataTracker() {

	}
}
