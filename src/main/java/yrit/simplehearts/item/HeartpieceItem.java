<<<<<<< HEAD

package yrit.simplehearts.item;

import yrit.simplehearts.procedures.HeartpieceRightClickedProcedure;
import yrit.simplehearts.init.SimpleHeartsModTabs;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class HeartpieceItem extends Item {
	public HeartpieceItem() {
		super(new Item.Properties().tab(SimpleHeartsModTabs.TAB_HEARTTAB).stacksTo(16).rarity(Rarity.UNCOMMON));
	}

	@Override
	public UseAnim getUseAnimation(ItemStack itemstack) {
		return UseAnim.EAT;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("Adds one heart of additional health."));
		list.add(new TextComponent("\u00A7cExpires on death."));
		list.add(new TextComponent("\u00A7aFull Heal"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		HeartpieceRightClickedProcedure.execute(world, x, y, z, entity);
		return ar;
	}
}
=======

package yrit.simplehearts.item;

import yrit.simplehearts.procedures.HeartpieceRightClickedProcedure;
import yrit.simplehearts.init.SimpleHeartsModTabs;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

public class HeartpieceItem extends Item {
	public HeartpieceItem() {
		super(new Item.Properties().tab(SimpleHeartsModTabs.TAB_HEARTTAB).stacksTo(16).rarity(Rarity.UNCOMMON));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("Adds one heart of additional health."));
		list.add(new TextComponent("\u00A7cExpires on death."));
		list.add(new TextComponent("\u00A7aFull Heal"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		HeartpieceRightClickedProcedure.execute(entity);
		return ar;
	}
}
>>>>>>> branch '1.18.2' of https://github.com/Yr1t/simplehearts.git
