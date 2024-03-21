
package net.yrit.simplehearts.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.List;

@JeiPlugin
public class SimpleHeartsModJeiInformation implements IModPlugin {
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("simple_hearts:information");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addIngredientInfo(List.of(new ItemStack(SimpleHeartsModItems.ETERNAL_HEART.get())), VanillaTypes.ITEM_STACK, Component.translatable("jei.simple_hearts.eternal_heart_info"));
		registration.addIngredientInfo(List.of(new ItemStack(SimpleHeartsModItems.ROTTEN_HEART.get()), new ItemStack(SimpleHeartsModItems.ROTTEN_HEART_PIECE.get())), VanillaTypes.ITEM_STACK,
				Component.translatable("jei.simple_hearts.rotten_heart_info"));
	}
}
