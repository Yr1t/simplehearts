
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.yrit.simplehearts.init;

import net.yrit.simplehearts.SimpleHeartsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class SimpleHeartsModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpleHeartsMod.MODID);
	public static final RegistryObject<CreativeModeTab> SIMPLE_HEARTS_TAB = REGISTRY.register("simple_hearts_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.simple_hearts.simple_hearts_tab")).icon(() -> new ItemStack(SimpleHeartsModItems.HEART_PIECE.get())).displayItems((parameters, tabData) -> {
				tabData.accept(SimpleHeartsModItems.HEART_PIECE.get());
				tabData.accept(SimpleHeartsModItems.HEART_CONTAINER.get());
				tabData.accept(SimpleHeartsModItems.ROTTEN_HEART.get());
				tabData.accept(SimpleHeartsModItems.ETERNAL_HEART.get());
				tabData.accept(SimpleHeartsModItems.ROTTEN_HEART_PIECE.get());
				tabData.accept(SimpleHeartsModItems.ROTTEN_SOUP.get());
			})

					.build());
}
