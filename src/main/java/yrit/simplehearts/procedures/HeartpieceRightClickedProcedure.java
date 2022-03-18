package yrit.simplehearts.procedures;

import yrit.simplehearts.init.SimpleHeartsModItems;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Iterator;

public class HeartpieceRightClickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
				.setBaseValue((((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() + 2));
		if (entity instanceof LivingEntity _entity)
			_entity.setHealth((float) ((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue());
		if (entity instanceof ServerPlayer _player) {
			Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("simple_hearts:firstdabble"));
			AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
			if (!_ap.isDone()) {
				Iterator _iterator = _ap.getRemainingCriteria().iterator();
				while (_iterator.hasNext())
					_player.getAdvancements().award(_adv, (String) _iterator.next());
			}
		}
		if (entity instanceof Player _player) {
			ItemStack _stktoremove = new ItemStack(SimpleHeartsModItems.HEARTPIECE.get());
			_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
		}
	}
}
