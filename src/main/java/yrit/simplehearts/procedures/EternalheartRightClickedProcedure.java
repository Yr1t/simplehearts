package yrit.simplehearts.procedures;

import yrit.simplehearts.item.EternalheartItem;
import yrit.simplehearts.SimpleHeartsModVariables;
import yrit.simplehearts.SimpleHeartsMod;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class EternalheartRightClickedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SimpleHeartsMod.LOGGER.warn("Failed to load dependency entity for procedure EternalheartRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts + 4);
			entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.Eternal_Hearts = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
				.setBaseValue((24 + ((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue()));
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A75You feel your endurance rising to new heights..."), (false));
		}
		if (entity instanceof PlayerEntity) {
			ItemStack _stktoremove = new ItemStack(EternalheartItem.block);
			((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
					((PlayerEntity) entity).container.func_234641_j_());
		}
	}
}
