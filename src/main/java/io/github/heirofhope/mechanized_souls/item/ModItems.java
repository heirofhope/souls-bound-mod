package io.github.heirofhope.mechanized_souls.item;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import io.github.heirofhope.mechanized_souls.item.custom.ContractItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ModItems {

	public static final Item FERROCIUM = registeritem("ferrocium",
		new Item(new QuiltItemSettings().group(ModItemGroup.HEIR_TAB)));


	public static final Item BOOK_CYPHER = registeritem("book_cypher",
		new Item(new QuiltItemSettings().maxCount(1).group(ModItemGroup.HEIR_TAB)));

	public static final Item CLOSED_CONTRACT = registeritem("closed_contract",
		new Item(new QuiltItemSettings().maxCount(1).group(ModItemGroup.HEIR_TAB)));

	public static final Item OPEN_CONTRACT = registeritem("open_contract",
		new ContractItem(new QuiltItemSettings().maxCount(1).group(ModItemGroup.HEIR_TAB)));


	public static final Item LEMON = registeritem("lemon",
		new Item(new QuiltItemSettings().food(new FoodComponent.Builder()
			.hunger(2).build()).group(ItemGroup.FOOD).maxCount(21)));

	public static final Item SOUL_COOKIE = registeritem("soul_cookie",
		new Item(new QuiltItemSettings().food(new FoodComponent.Builder()
			.hunger(4).build()).group(ModItemGroup.HEIR_TAB).maxCount(16)));








	private static Item registeritem(String name,Item item) {
	return Registry.register(Registry.ITEM, new Identifier(MechanizedSouls.MOD_ID, name), item);
}

	public  static  void RegisterModItems() {
		MechanizedSouls.LOGGER.debug("Registering Mod items for" + MechanizedSouls.MOD_ID);
	}
}

