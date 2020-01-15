package slimeknights.tconstruct.world.worldgen;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import net.minecraft.world.gen.feature.TreeFeatureConfig;
import slimeknights.tconstruct.blocks.WorldBlocks;

import javax.annotation.Nullable;
import java.util.Random;

public class BlueSlimeTree extends Tree {

  private final boolean isIslandTree;

  public BlueSlimeTree(boolean isIslandTree) {
    this.isIslandTree = isIslandTree;
  }

  @Override
  @Nullable
  protected ConfiguredFeature<TreeFeatureConfig, ?> func_225546_b_(Random random) {
    if (this.isIslandTree) {
      return new SlimeTreeFeature(NoFeatureConfig::deserialize, true, 5, 4, WorldBlocks.congealed_green_slime.getDefaultState(), WorldBlocks.blue_slime_leaves.getDefaultState(), WorldBlocks.blue_slime_vine_middle.getDefaultState(), WorldBlocks.blue_slime_sapling, true);
    }
    else {
      return new SlimeTreeFeature(NoFeatureConfig::deserialize, true, 5, 4, WorldBlocks.congealed_green_slime.getDefaultState(), WorldBlocks.blue_slime_leaves.getDefaultState(), null, WorldBlocks.blue_slime_sapling, true);
    }
  }
}