package net.yrit.simplehearts.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigInitProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		File config = new File("");
		com.google.gson.JsonObject configJsonObject = new com.google.gson.JsonObject();
		config = new File((FMLPaths.GAMEDIR.get().toString() + "/config"), File.separator + "simplehearts.json");
		if (!config.exists()) {
			try {
				config.getParentFile().mkdirs();
				config.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			configJsonObject.addProperty("Version", 3);
			configJsonObject.addProperty("HeartPieceLimit", 10);
			configJsonObject.addProperty("HeartContainerLimit", 10);
			configJsonObject.addProperty("EternalHeartLimit", 2);
			configJsonObject.addProperty("GenerateShack", true);
			configJsonObject.addProperty("SubtractHealthBy", 0);
			{
				Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(config);
					fileWriter.write(mainGSONBuilderVariable.toJson(configJsonObject));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
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
				configJsonObject = new Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (configJsonObject.get("Version").getAsDouble() != 3) {
					try {
						config.getParentFile().mkdirs();
						config.createNewFile();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
					configJsonObject.addProperty("Version", 3);
					configJsonObject.addProperty("HeartPieceLimit", 10);
					configJsonObject.addProperty("HeartContainerLimit", 10);
					configJsonObject.addProperty("EternalHeartLimit", 2);
					configJsonObject.addProperty("GenerateShack", true);
					configJsonObject.addProperty("SubtractHealthBy", 0);
					{
						Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
						try {
							FileWriter fileWriter = new FileWriter(config);
							fileWriter.write(mainGSONBuilderVariable.toJson(configJsonObject));
							fileWriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
