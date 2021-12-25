package yrit.simplehearts.procedures;

import yrit.simplehearts.SimpleHeartsModVariables;
import yrit.simplehearts.SimpleHeartsMod;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class WipeEXhealthCommandExecutedProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SimpleHeartsMod.LOGGER.warn("Failed to load dependency entity for procedure WipeEXhealthCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			double _setval = 0;
			entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.EX_Hearts = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			double _setval = 0;
			entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.Eternal_Hearts = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
			((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(("All extra hearts have been wiped from " + entity + ".")), (false));
		}
	}
}
