package io.github.heirofhope.mechanized_souls.item.custom;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class DawnCleaverItem extends AxeItem {



	public DawnCleaverItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings QuiltItemSettings) {
		super(material, attackDamage, attackSpeed,QuiltItemSettings);
		class ReachModification {

			// Define a custom reach modifier (in blocks)
			public static final double customReachDistance = 6.0; // default reach value is 5.0

			// Event listener to modify reach distance
			public static void onReachEntity(PlayerReachEntityEvent event, Item DawnCleaverItem) {
				PlayerEntity player = event.getPlayer();

				// Change the reach distance if player is holding a specific item (e.g., a custom weapon)
				if (player.getMainHandStack().getItem() == DawnCleaverItem) {  // Change condition to check for your custom weapon
					event.setReachDistance(customReachDistance);
				}
			}

			// Custom event to handle changes when player interacts with entities
			public static class PlayerReachEntityEvent extends Event {
				private final PlayerEntity player;
				private double reachDistance;

				public PlayerReachEntityEvent(PlayerEntity player, double reachDistance) {
					this.player = player;
					this.reachDistance = reachDistance;
				}

				public PlayerEntity getPlayer() {
					return player;
				}

				public double getReachDistance() {
					return reachDistance;
				}

				public void setReachDistance(double reachDistance) {
					this.reachDistance = reachDistance;
				}

				/**
				 * Register a listener to the event, in the default phase.
				 * Have a look at {@link #addPhaseOrdering} for an explanation of event phases.
				 *
				 * @param listener The desired listener.
				 */
				@Override
				public void register(Object listener) {

				}
			}
		}
	}
}
