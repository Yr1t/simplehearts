
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package yrit.simplehearts.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class SimpleHeartsModTabs {
	public static CreativeModeTab TAB_HEARTTAB;

	public static void load() {
		TAB_HEARTTAB = new CreativeModeTab("tabhearttab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(SimpleHeartsModItems.HEARTPIECE.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
