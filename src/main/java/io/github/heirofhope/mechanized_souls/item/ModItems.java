package io.github.heirofhope.mechanized_souls.item;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.item.custom.*;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ModItems {

	// Heir's Tomfoolery:

	public static final Item FERROCIUM = registeritem("ferrocium",
		new Item(new QuiltItemSettings().group(ModItemGroup.HEIR_TAB)));

	public static final Item KNIGHT_SPAWNEGG = registeritem("knight_spawnegg",
		new KnightSpawnItem(new QuiltItemSettings().group(ModItemGroup.HEIR_TAB)));

	public static final Item HALBERD = registeritem("halberd",
		new HalberdItem(ModToolMaterials.FERROCIUM, 5, -3.5f,
			new QuiltItemSettings().group(ModItemGroup.HEIR_TAB)));

	public static final Item soul_cookie = registeritem("soul_cookie",
		new Item(new QuiltItemSettings().maxCount(16).food(new FoodComponent.Builder()
			.hunger(4).build()).group(ModItemGroup.HEIR_TAB)));

	// Fox's Tomfoolery:

	public static final Item DAWN_CLEAVER = registeritem("dawn_cleaver",
		new DawnCleaverItem(ModToolMaterials.FOXIUM, 6, -3.75f,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));

	public static final Item ROCK_SWORD = registeritem("rock_sword",
		new DawnCleaverItem(ModToolMaterials.FOXIUM, 0, -2.5f,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));

	public static final Item SOUL_SCYTHE = registeritem("soul_scythe",
		new SwordItem(ModToolMaterials.FOXIUM, 0, -3.50f,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));

	public static final Item book_cypher = registeritem("book_cypher",
		new BookCypher(new QuiltItemSettings().maxCount(1).group(ModItemGroup.FOX_TAB)));

	public static final Item open_contract = registeritem("open_contract",
		new ContractItem(new QuiltItemSettings().maxCount(1).group(ModItemGroup.FOX_TAB)));

	public static final Item closed_contract = registeritem("closed_contract",
		new ClosedContractItem(new QuiltItemSettings().maxCount(1).group(ModItemGroup.FOX_TAB)));

	public static final Item lemon = registeritem("lemon",
		new Item(new QuiltItemSettings().food(new FoodComponent.Builder().hunger(2).build()).group(ModItemGroup.FOX_TAB)));

	public static final Item recall_puppet = registeritem("recall_puppet",
		new Item(new QuiltItemSettings().maxCount(1).group(ModItemGroup.FOX_TAB)));

	public static final Item soul_drain = registeritem("soul_drain",
		new Item(new QuiltItemSettings().maxCount(1).group(ModItemGroup.FOX_TAB)));

	public static final Item SUSSY_ITEM = registeritem("sussy_item",
		new SwordItem(ModToolMaterials.FOXIUM, 9999, 9999,
			new QuiltItemSettings()));

	private static Item registeritem(String name, Item DawncleaverItem) {
		return Registry.register(Registry.ITEM, new Identifier(MechanizedSouls.MOD_ID, name), DawncleaverItem);
	}

	public static void RegisterModItems() {
		MechanizedSouls.LOGGER.debug("Registering Mod items for" + MechanizedSouls.MOD_ID);
	}
}
