package yrit.simplehearts.procedures;

import yrit.simplehearts.SimpleHeartsModVariables;
import yrit.simplehearts.SimpleHeartsMod;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

public class PoisonsoupFoodEatenProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SimpleHeartsMod.LOGGER.warn("Failed to load dependency entity for procedure PoisonsoupFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts <= 0) {
			{
				double _setval = ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts - 2);
				entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Eternal_Hearts = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else {
			{
				double _setval = ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts - 2);
				entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.EX_Hearts = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts < 0) {
			{
				double _setval = 0;
				entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.EX_Hearts = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts < 0) {
			{
				double _setval = 0;
				entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Eternal_Hearts = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
				.setBaseValue((((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() - 2));
		if (((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() < 20) {
			((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
		}
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) 200, (int) 2));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.HUNGER, (int) 200, (int) 4));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 200, (int) 8));
	}
}
