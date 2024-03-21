package net.yrit.simplehearts.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class AbandonedShackCheckConfigProcedure {
	public static boolean execute() {
		File config = new File("");
		com.google.gson.JsonObject configJsonObject = new com.google.gson.JsonObject();
		boolean Yesington = false;
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
				if (configJsonObject.get("GenerateShack").getAsBoolean() == true) {
					Yesington = true;
				} else {
					Yesington = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (Yesington == true) {
			return true;
		}
		return false;
	}
}
