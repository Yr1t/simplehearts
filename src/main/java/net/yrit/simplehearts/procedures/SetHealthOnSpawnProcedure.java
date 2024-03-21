package net.yrit.simplehearts.procedures;

import net.yrit.simplehearts.network.SimpleHeartsModVariables;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

@Mod.EventBusSubscriber
public class SetHealthOnSpawnProcedure {
	@SubscribeEvent
	public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		File config = new File("");
		com.google.gson.JsonObject configJsonObject = new com.google.gson.JsonObject();
		double TotalAdditionalHearts = 0;
		config = new File((FMLPaths.GAMEDIR.get().toString() + "/config"), File.separator + "simplehearts.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(config));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				configJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SimpleHeartsModVariables.PlayerVariables())).HeartContainerCount > configJsonObject.get("HeartContainerLimit").getAsDouble()) {
					{
						double _setval = configJsonObject.get("HeartContainerLimit").getAsDouble();
						entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.HeartContainerCount = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("Your health is lower due to the Heart Container limit being changed. "), false);
				} else if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SimpleHeartsModVariables.PlayerVariables())).EternalHeartCount > configJsonObject.get("EternalHeartLimit").getAsDouble()) {
					{
						double _setval = configJsonObject.get("EternalHeartLimit").getAsDouble();
						entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.EternalHeartCount = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("Your health is lower due to the Eternal Heart limit being changed. "), false);
				}
				((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
						.setBaseValue(((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SimpleHeartsModVariables.PlayerVariables())).HeartContainerCount * 2
								+ (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SimpleHeartsModVariables.PlayerVariables())).EternalHeartCount * 10
								+ ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() - configJsonObject.get("SubtractHealthBy").getAsDouble()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
