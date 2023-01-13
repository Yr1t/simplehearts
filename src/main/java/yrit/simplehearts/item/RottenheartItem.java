
package yrit.simplehearts.item;

import yrit.simplehearts.init.SimpleHeartsModTabs;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class RottenheartItem extends Item {
	public RottenheartItem() {
		super(new Item.Properties().tab(SimpleHeartsModTabs.TAB_HEARTTAB).stacksTo(16).rarity(Rarity.COMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("Currently unusable as a health source."));
		list.add(new TextComponent("\uFFFD6Maybe if you purified it..."));
	}
}
