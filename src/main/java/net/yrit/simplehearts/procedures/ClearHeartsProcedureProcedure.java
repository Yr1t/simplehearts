package net.yrit.simplehearts.procedures;

import net.yrit.simplehearts.network.SimpleHeartsModVariables;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class ClearHeartsProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject configJsonObject = new com.google.gson.JsonObject();
		File config = new File("");
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
				{
					double _setval = 0;
					entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.HeartPieceCount = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = 0;
					entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.HeartContainerCount = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = 0;
					entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.EternalHeartCount = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Do /kill for the health to go away."), false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
