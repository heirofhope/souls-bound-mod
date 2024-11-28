package io.github.heirofhope.mechanized_souls.item;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.item.custom.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ModItems {

//   Heir's Tomfoolery:

	public static final Item FERROCIUM = registeritem("ferrocium",
		new Item(new QuiltItemSettings().group(ModItemGroup.HEIR_TAB)));

	public static final Item KNIGHT_SPAWNEGG = registeritem("knight_spawnegg",
		new KnightSpawnItem(new QuiltItemSettings().group(ModItemGroup.HEIR_TAB)));

	public static final Item HALBERD = registeritem("halberd",
		new HalberdItem(ModToolMaterials.FERROCIUM, 5,-3.5f,
			new QuiltItemSettings().group(ModItemGroup.HEIR_TAB)));

	public static final Item soul_cookie = registeritem("soul_cookie",
		new Item(new QuiltItemSettings().maxCount(16).food(new FoodComponent.Builder()
			.hunger(4).build()).group(ModItemGroup.HEIR_TAB)));



//    Fox's Tomfoolery:



	public static final Item DAWN_CLEAVER = registeritem("dawn_cleaver",
		new DawnCleaverItem(ModToolMaterials.FOXIUM, 7,-3.5f,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));

	public static final Item SOUL_SCYTHE = registeritem("soul_scythe",
		new SwordItem(ModToolMaterials.FOXIUM, 0,-2.9f,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));

	public static final Item book_cypher = registeritem("book_cypher",
		new Item(new QuiltItemSettings().maxCount(1).group(ModItemGroup.FOX_TAB)));

	public static final Item open_contract = registeritem("open_contract",
		new ContractItem(new QuiltItemSettings().maxCount(1).group(ModItemGroup.FOX_TAB)));

	public static final Item closed_contract = registeritem("closed_contract",
		new ClosedContractItem(new QuiltItemSettings().maxCount(1).group(ModItemGroup.FOX_TAB)));

	public static final Item lemon = registeritem("lemon",
		new Item(new QuiltItemSettings().food(new FoodComponent.Builder().hunger(2).build()).group(ModItemGroup.FOX_TAB)));


// Aristu's Tomfoolery

	public static final Item FERROCIUM_HELMET = registeritem("ferrocium_helmet",
		new ArmorItem(ModArmorMaterials.FERROCIUM, EquipmentSlot.HEAD,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));
	public static final Item FERROCIUM_CHESTPLATE = registeritem("ferrocium_chestplate",
		new ArmorItem(ModArmorMaterials.FERROCIUM, EquipmentSlot.CHEST,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));
	public static final Item FERROCIUM_LEGGINGS = registeritem("ferrocium_leggings",
		new ArmorItem(ModArmorMaterials.FERROCIUM, EquipmentSlot.LEGS,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));
	public static final Item FERROCIUM_BOOTS = registeritem("ferrocium_boots",
		new ArmorItem(ModArmorMaterials.FERROCIUM, EquipmentSlot.FEET,
			new QuiltItemSettings().group(ModItemGroup.FOX_TAB)));



//	  Hour's Tomfoolery;


	//This Item is a debug item, DO NOT DELETE!
	public static final Item SUSSY_ITEM = registeritem("sussy_item",
		new SwordItem(ModToolMaterials.FOXIUM, 9999,9999,
			new QuiltItemSettings().group(ModItemGroup.HOUR_TAB)));

	public static final Item ECLIPSE_CLAYMORE = registeritem("eclipse_claymore",
		new HalberdItem(ModToolMaterials.FERROCIUM, 5,-3.5f,
			new QuiltItemSettings().group(ModItemGroup.HOUR_TAB)));



	private static Item registeritem(String name,Item item) {
	return Registry.register(Registry.ITEM, new Identifier(MechanizedSouls.MOD_ID, name), item);
}

	public  static  void RegisterModItems() {



		MechanizedSouls.LOGGER.debug("Registering Mod items for" + MechanizedSouls.MOD_ID);
	}
}

