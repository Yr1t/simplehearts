
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.yrit.simplehearts.init;

import net.yrit.simplehearts.item.RottenSoupItem;
import net.yrit.simplehearts.item.RottenHeartPieceItem;
import net.yrit.simplehearts.item.RottenHeartItem;
import net.yrit.simplehearts.item.HeartPieceItem;
import net.yrit.simplehearts.item.HeartContainerItem;
import net.yrit.simplehearts.item.EternalHeartItem;
import net.yrit.simplehearts.SimpleHeartsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

public class SimpleHeartsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SimpleHeartsMod.MODID);
	public static final RegistryObject<Item> HEART_PIECE = REGISTRY.register("heart_piece", () -> new HeartPieceItem());
	public static final RegistryObject<Item> HEART_CONTAINER = REGISTRY.register("heart_container", () -> new HeartContainerItem());
	public static final RegistryObject<Item> ROTTEN_HEART = REGISTRY.register("rotten_heart", () -> new RottenHeartItem());
	public static final RegistryObject<Item> ETERNAL_HEART = REGISTRY.register("eternal_heart", () -> new EternalHeartItem());
	public static final RegistryObject<Item> ROTTEN_HEART_PIECE = REGISTRY.register("rotten_heart_piece", () -> new RottenHeartPieceItem());
	public static final RegistryObject<Item> ROTTEN_SOUP = REGISTRY.register("rotten_soup", () -> new RottenSoupItem());
}
