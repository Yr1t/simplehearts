package yrit.simplehearts.procedures;

import yrit.simplehearts.network.SimpleHeartsModVariables;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Iterator;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import com.google.gson.Gson;

public class HeartcontainerRightClickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		File config = new File("");
		com.google.gson.JsonObject simpleheartjson = new com.google.gson.JsonObject();
		config = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "simplehearts_config.json");
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
				if ((entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(
						new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts >= simpleheartjson.get("MaxHeartContainers").getAsDouble()) {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent("You have the maximum amount of Heart Containers!"), (false));
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.note_block.bass")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.note_block.bass")),
									SoundSource.NEUTRAL, 1, 1, false);
						}
					}
				} else {
					{
						double _setval = (entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new SimpleHeartsModVariables.PlayerVariables())).EX_Hearts + 2;
						entity.getCapability(SimpleHeartsModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.EX_Hearts = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
					((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH)
							.setBaseValue((((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue() + 2));
					if (entity instanceof LivingEntity _entity)
						_entity.setHealth((float) ((LivingEntity) entity).getAttribute(Attributes.MAX_HEALTH).getBaseValue());
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("simple_hearts:firstdabble"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							Iterator _iterator = _ap.getRemainingCriteria().iterator();
							while (_iterator.hasNext())
								_player.getAdvancements().award(_adv, (String) _iterator.next());
						}
					}
					if (entity instanceof ServerPlayer _player) {
						Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("simple_hearts:cant_contain_me"));
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							Iterator _iterator = _ap.getRemainingCriteria().iterator();
							while (_iterator.hasNext())
								_player.getAdvancements().award(_adv, (String) _iterator.next());
						}
					}
					if (entity instanceof Player _player) {
						ItemStack _stktoremove = itemstack;
						_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1,
								_player.inventoryMenu.getCraftSlots());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
