package io.github.heirofhope.mechanized_souls.item;

import io.github.heirofhope.mechanized_souls.MechanizedSouls;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ModItems {

	public static final Item FERROCIUM = registeritem("ferrocium",
		new Item(new QuiltItemSettings().group(ItemGroup.MISC)));
	public static final Item lemon = registeritem("lemon",
		new Item(new QuiltItemSettings().food(new FoodComponent.Builder().hunger(2).build()).group(ItemGroup.FOOD)));

	public static final Item soul_cookie = registeritem("soul_cookie",
		new Item(new QuiltItemSettings().food(new FoodComponent.Builder().hunger(4).build()).group(ItemGroup.FOOD)));
	public static final Item book_cypher = registeritem("book_cypher",
		new Item(new QuiltItemSettings().group(ItemGroup.MISC)));
	public static final Item closed_contract = registeritem("closed_contract",
		new WrittenBookItem (new QuiltItemSettings().group(ItemGroup.MISC)));
	public static final Item open_contract = registeritem("open_contract",
		new WritableBookItem (new QuiltItemSettings().group(ItemGroup.MISC)));








	private static Item registeritem(String name,Item item) {
	return Registry.register(Registry.ITEM, new Identifier(MechanizedSouls.MOD_ID, name), item);
}

	public  static  void RegisterModItems() {



		MechanizedSouls.LOGGER.debug("Registering Mod items for" + MechanizedSouls.MOD_ID);
	}
}

