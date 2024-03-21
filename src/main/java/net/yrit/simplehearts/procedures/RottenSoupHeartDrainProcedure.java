package net.yrit.simplehearts.procedures;

import net.yrit.simplehearts.network.SimpleHeartsModVariables;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class RottenSoupHeartDrainProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		File config = new File("");
		com.google.gson.JsonObject configJsonObject = new com.google.gson.JsonObject();
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
				if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SimpleHeartsModVariables.PlayerVariables())).HeartContainerCount > 0) {
					{
						double _setval = (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SimpleHeartsModVariables.PlayerVariables())).HeartContainerCount - 1;
						entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.HeartContainerCount = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
							.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() - 2));
					if (entity instanceof LivingEntity _entity)
						_entity.setHealth((float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue());
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("Sacrificing a Heart Container."), true);
					itemstack.shrink(1);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
				} else if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SimpleHeartsModVariables.PlayerVariables())).EternalHeartCount > 0) {
					{
						double _setval = (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new SimpleHeartsModVariables.PlayerVariables())).EternalHeartCount - 1;
						entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.EternalHeartCount = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH)
							.setBaseValue((((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getBaseValue() - 10));
					if (entity instanceof LivingEntity _entity)
						_entity.setHealth((float) ((LivingEntity) entity).getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH).getValue());
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("Sacrificing an Eternal Heart."), true);
					itemstack.shrink(1);
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));
				} else {
					if (entity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("You have no hearts left to give."), true);
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.note_block.bass")), SoundSource.RECORDS, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.note_block.bass")), SoundSource.RECORDS, 1, 1, false);
						}
					}
					if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
