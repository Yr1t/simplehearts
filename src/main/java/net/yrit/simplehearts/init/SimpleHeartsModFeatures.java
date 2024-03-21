
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.yrit.simplehearts.init;

import net.yrit.simplehearts.world.features.AbandonedShackFeature;
import net.yrit.simplehearts.SimpleHeartsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

@Mod.EventBusSubscriber
public class SimpleHeartsModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, SimpleHeartsMod.MODID);
	public static final RegistryObject<Feature<?>> ABANDONED_SHACK = REGISTRY.register("abandoned_shack", AbandonedShackFeature::new);
}
