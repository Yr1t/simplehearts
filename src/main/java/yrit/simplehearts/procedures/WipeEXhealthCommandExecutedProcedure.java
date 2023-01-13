<<<<<<< HEAD
package yrit.simplehearts.procedures;

import yrit.simplehearts.network.SimpleHeartsModVariables;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;


import java.io.File;

public class WipeEXhealthCommandExecutedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double Combined_EX_Hearts = 0;
		File config = new File("");
		com.google.gson.JsonObject simpleheartjson = new com.google.gson.JsonObject();
		config = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "simplehearts_config.json");
		Combined_EX_Hearts = (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts
				+ (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts;
		((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
				.setBaseValue((((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() - Combined_EX_Hearts));
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
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("All extra Heart Containers have been wiped from your soul."), (false));
	}
}
=======
package yrit.simplehearts.procedures;

import yrit.simplehearts.network.SimpleHeartsModVariables;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;


import java.io.File;

public class WipeEXhealthCommandExecutedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double Combined_EX_Hearts = 0;
		File config = new File("");
		com.google.gson.JsonObject simpleheartjson = new com.google.gson.JsonObject();
		config = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "simplehearts_config.json");
		Combined_EX_Hearts = (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts
				+ (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts;
		((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
				.setBaseValue((((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() - Combined_EX_Hearts));
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
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent("All extra Heart Containers have been wiped from your soul."), (false));
	}
}
>>>>>>> branch '1.18.2' of https://github.com/Yr1t/simplehearts.git
