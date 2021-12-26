package yrit.simplehearts.procedures;

import yrit.simplehearts.SimpleHeartsModVariables;
import yrit.simplehearts.SimpleHeartsMod;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class PoisonsoupFoodEatenProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SimpleHeartsMod.LOGGER.warn("Failed to load dependency entity for procedure PoisonsoupFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		File config = new File("");
		com.google.gson.JsonObject simpleheartjson = new com.google.gson.JsonObject();
		config = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "simplehearts.json");
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
		if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts <= 0
				&& (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts <= 0) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A74You have no Heart Containers left to give up!"), (false));
			}
		} else {
			((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
					.setBaseValue((((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() - 2));
		}
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(config));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				simpleheartjson = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (simpleheartjson.get("StartingHealthToggle").getAsBoolean() == true) {
					if (((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() < simpleheartjson.get("StartingHealth")
							.getAsDouble()) {
						((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).setBaseValue(simpleheartjson.get("StartingHealth").getAsDouble());
					}
				} else if (((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() < 20) {
					((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, (int) 200, (int) 2));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.HUNGER, (int) 200, (int) 4));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 200, (int) 8));
	}
}
