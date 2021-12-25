
package yrit.simplehearts.itemgroup;

import yrit.simplehearts.item.HeartpieceItem;
import yrit.simplehearts.SimpleHeartsModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@SimpleHeartsModElements.ModElement.Tag
public class HearttabItemGroup extends SimpleHeartsModElements.ModElement {
	public HearttabItemGroup(SimpleHeartsModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabhearttab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(HeartpieceItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
