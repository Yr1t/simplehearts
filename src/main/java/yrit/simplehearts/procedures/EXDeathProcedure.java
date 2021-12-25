package yrit.simplehearts.procedures;

import yrit.simplehearts.SimpleHeartsModVariables;
import yrit.simplehearts.SimpleHeartsMod;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class EXDeathProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
			Entity entity = event.getPlayer();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", entity.getPosX());
			dependencies.put("y", entity.getPosY());
			dependencies.put("z", entity.getPosZ());
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("endconquered", event.isEndConquered());
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				SimpleHeartsMod.LOGGER.warn("Failed to load dependency entity for procedure EXDeath!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		File config = new File("");
		com.google.gson.JsonObject simpleheartjson = new com.google.gson.JsonObject();
		config = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "simplehearts.json");
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
				if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts > simpleheartjson.get("MaxAmount").getAsDouble()) {
					{
						double _setval = simpleheartjson.get("MaxAmount").getAsDouble();
						entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.EX_Hearts = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
								"Some Heart Containers may have been removed due to the Heart Container limit being changed."), (false));
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
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
					((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).setBaseValue(simpleheartjson.get("StartingHealth").getAsDouble());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
				.setBaseValue(((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts
						+ ((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue()));
		((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
				.setBaseValue(((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts
						+ ((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue()));
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).setHealth((float) ((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue());
	}
}
