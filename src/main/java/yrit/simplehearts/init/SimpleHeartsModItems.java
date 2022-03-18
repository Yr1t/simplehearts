
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package yrit.simplehearts.init;

import yrit.simplehearts.item.RottenheartItem;
import yrit.simplehearts.item.PoisonsoupItem;
import yrit.simplehearts.item.HeartpieceItem;
import yrit.simplehearts.item.HeartcontainerItem;
import yrit.simplehearts.item.EternalheartItem;
import yrit.simplehearts.SimpleHeartsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

public class SimpleHeartsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SimpleHeartsMod.MODID);
	public static final RegistryObject<Item> HEARTPIECE = REGISTRY.register("heartpiece", () -> new HeartpieceItem());
	public static final RegistryObject<Item> HEARTCONTAINER = REGISTRY.register("heartcontainer", () -> new HeartcontainerItem());
	public static final RegistryObject<Item> ROTTENHEART = REGISTRY.register("rottenheart", () -> new RottenheartItem());
	public static final RegistryObject<Item> ETERNALHEART = REGISTRY.register("eternalheart", () -> new EternalheartItem());
	public static final RegistryObject<Item> POISONSOUP = REGISTRY.register("poisonsoup", () -> new PoisonsoupItem());
}
