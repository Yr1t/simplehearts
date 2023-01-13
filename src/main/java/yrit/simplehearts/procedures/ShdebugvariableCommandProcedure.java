<<<<<<< HEAD
package yrit.simplehearts.procedures;

import yrit.simplehearts.network.SimpleHeartsModVariables;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;


public class ShdebugvariableCommandProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					new TextComponent(("EX_Hearts: " + (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts)),
					(false));
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					new TextComponent(("Eternal_Hearts: " + (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts)),
					(false));
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					new TextComponent(("Max Health" + ((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue())), (false));
	}
}
=======
package yrit.simplehearts.procedures;

import yrit.simplehearts.network.SimpleHeartsModVariables;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;


public class ShdebugvariableCommandProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					new TextComponent(("EX_Hearts: " + (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts)),
					(false));
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					new TextComponent(("Eternal_Hearts: " + (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new SimpleHeartsModVariables.PlayerVariables())).Eternal_Hearts)),
					(false));
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(
					new TextComponent(("Max Health" + ((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue())), (false));
	}
}
>>>>>>> branch '1.18.2' of https://github.com/Yr1t/simplehearts.git
