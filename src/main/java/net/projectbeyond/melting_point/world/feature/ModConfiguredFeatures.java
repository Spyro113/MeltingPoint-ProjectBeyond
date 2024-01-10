package net.projectbeyond.melting_point.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.projectbeyond.melting_point.MeltingPoint;
import net.projectbeyond.melting_point.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> LEAD_ORE_KEY = registerKey("lead_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TUNGSTEN_ORE_KEY = registerKey("tungsten_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> NETHER_TUNGSTEN_ORE_KEY = registerKey("nether_tungsten_ore");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldLeadOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.LEAD_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_LEAD_ORE.getDefaultState()));
        register(context, LEAD_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldLeadOres, 11));

        List<OreFeatureConfig.Target> overworldTungstenOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.TUNGSTEN_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_TUNGSTEN_ORE.getDefaultState()));
        register(context, TUNGSTEN_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldTungstenOres, 9));

        /*List<OreFeatureConfig.Target> netherTungstenOres =
                List.of(OreFeatureConfig.createTarget(netherReplacables, ModBlocks.NETHER_TUNGSTEN_ORE.getDefaultState()));
        register(context, NETHER_TUNGSTEN_ORE_KEY, Feature.ORE, new OreFeatureConfig(netherTungstenOres, 10));*/

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MeltingPoint.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}