
package net.yrit.simplehearts.world.features;

import net.yrit.simplehearts.world.features.configurations.StructureFeatureConfiguration;
import net.yrit.simplehearts.procedures.AbandonedShackCheckConfigProcedure;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.WorldGenLevel;

public class AbandonedShackFeature extends StructureFeature {
	public AbandonedShackFeature() {
		super(StructureFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<StructureFeatureConfiguration> context) {
		WorldGenLevel world = context.level();
		int x = context.origin().getX();
		int y = context.origin().getY();
		int z = context.origin().getZ();
		if (!AbandonedShackCheckConfigProcedure.execute())
			return false;
		return super.place(context);
	}
}
