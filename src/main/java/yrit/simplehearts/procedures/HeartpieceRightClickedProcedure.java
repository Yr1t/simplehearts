package yrit.simplehearts.procedures;

import yrit.simplehearts.item.HeartpieceItem;
import yrit.simplehearts.SimpleHeartsMod;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class HeartpieceRightClickedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SimpleHeartsMod.LOGGER.warn("Failed to load dependency entity for procedure HeartpieceRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
				.setBaseValue((((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() + 2));
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(HeartpieceItem.block);
			((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
					((PlayerEntity) entity).container.func_234641_j_());
		}
	}
}
