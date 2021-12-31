package yrit.simplehearts.procedures;

import yrit.simplehearts.SimpleHeartsMod;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;
import java.util.Collections;

import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;

import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public class HeartpiececonfigProcedure {
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void init(FMLCommonSetupEvent event) {
			executeProcedure(Collections.emptyMap());
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		File config = new File("");
		com.google.gson.JsonObject simpleheartjson = new com.google.gson.JsonObject();
		config = (File) new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "simplehearts_config.json");
		if (config.exists()) {
			SimpleHeartsMod.LOGGER.info("Simple Hearts config found.");
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
					if (simpleheartjson.get("Version").getAsDouble() < 1) {
						try {
							config.getParentFile().mkdirs();
							config.createNewFile();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
						simpleheartjson.addProperty("Version", 1);
						simpleheartjson.addProperty("MaxHeartContainers", 40);
						simpleheartjson.addProperty("StartingHealthToggle", (false));
						simpleheartjson.addProperty("StartingHealth", 20);
						simpleheartjson.addProperty("EternalHeartTemp", 8);
						try {
							FileWriter configwriter = new FileWriter(config);
							BufferedWriter configbw = new BufferedWriter(configwriter);

							{
								Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
								try {
									FileWriter fileWriter = new FileWriter(config);
									fileWriter.write(mainGSONBuilderVariable.toJson(simpleheartjson));
									fileWriter.close();
								} catch (IOException exception) {
									exception.printStackTrace();
								}
							}
							configbw.close();
							configwriter.close();
						} catch (IOException exception) {
							exception.printStackTrace();
						}
						SimpleHeartsMod.LOGGER.warn("Simple Hearts config update detected, config reset.");
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				config.getParentFile().mkdirs();
				config.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			simpleheartjson.addProperty("Version", 1);
			simpleheartjson.addProperty("MaxHeartContainers", 40);
			simpleheartjson.addProperty("StartingHealthToggle", (false));
			simpleheartjson.addProperty("StartingHealth", 20);
			simpleheartjson.addProperty("EternalHeartTemp", 8);
			try {
				FileWriter configwriter = new FileWriter(config);
				BufferedWriter configbw = new BufferedWriter(configwriter);

				{
					Gson mainGSONBuilderVariable = new GsonBuilder().setPrettyPrinting().create();
					try {
						FileWriter fileWriter = new FileWriter(config);
						fileWriter.write(mainGSONBuilderVariable.toJson(simpleheartjson));
						fileWriter.close();
					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
				configbw.close();
				configwriter.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			SimpleHeartsMod.LOGGER.info("Simple Hearts config created and loaded.");
		}
	}
}
