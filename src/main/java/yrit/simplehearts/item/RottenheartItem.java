
package yrit.simplehearts.item;

import yrit.simplehearts.itemgroup.HearttabItemGroup;
import yrit.simplehearts.SimpleHeartsModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import java.util.List;

@SimpleHeartsModElements.ModElement.Tag
public class RottenheartItem extends SimpleHeartsModElements.ModElement {
	@ObjectHolder("simple_hearts:rottenheart")
	public static final Item block = null;

	public RottenheartItem(SimpleHeartsModElements instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(HearttabItemGroup.tab).maxStackSize(16).rarity(Rarity.COMMON));
			setRegistryName("rottenheart");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("Currently unusable as a health source."));
			list.add(new StringTextComponent("\u00A76Maybe if you purified it..."));
		}
	}
}
