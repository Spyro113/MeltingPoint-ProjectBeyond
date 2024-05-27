package net.projectbeyond.melting_point.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.BlockView;
import net.projectbeyond.melting_point.MeltingPoint;

public class ModBlocks {
    //Sep

    public static final Block TIN_ORE = registerBlock("tin_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.STONE).strength(2.25f,2.75f).
                    requiresTool() ));
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.DEEPSLATE).strength(4.0f,2.75f).
                    requiresTool() ));
    public static final Block TIN_BLOCK = registerBlock("tin_block", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(3.0f,6.0f).
                    requiresTool() ));

    public static final Block BRONZE_BLOCK = registerBlock("bronze_block", false,
            new AgingBlock(Aging.AgingLevel.UNAFFECTED, FabricBlockSettings.create().sounds(BlockSoundGroup.COPPER).strength(5.0f,10.0f).
                    requiresTool() ));
    public static final Block AGED_BRONZE_BLOCK = registerBlock("aged_bronze_block", false,
            new AgingBlock(Aging.AgingLevel.AGED,
                    FabricBlockSettings.copyOf(ModBlocks.BRONZE_BLOCK) ));
    public static final Block ANCIENT_BRONZE_BLOCK = registerBlock("ancient_bronze_block", false,
            new AgingBlock(Aging.AgingLevel.ANCIENT,
                    FabricBlockSettings.copyOf(ModBlocks.BRONZE_BLOCK) ));
    public static final Block CHISELED_BRONZE_BLOCK = registerBlock("chiseled_bronze_block", false,
            new AgingBlock(Aging.AgingLevel.UNAFFECTED, FabricBlockSettings.create().sounds(BlockSoundGroup.COPPER).strength(5.0f,10.0f).
                    requiresTool() ));
    public static final Block AGED_CHISELED_BRONZE_BLOCK = registerBlock("aged_chiseled_bronze_block", false,
            new AgingBlock(Aging.AgingLevel.AGED,
                    FabricBlockSettings.copyOf(ModBlocks.BRONZE_BLOCK) ));
    public static final Block ANCIENT_CHISELED_BRONZE_BLOCK = registerBlock("ancient_chiseled_bronze_block", false,
            new AgingBlock(Aging.AgingLevel.ANCIENT,
                    FabricBlockSettings.copyOf(ModBlocks.BRONZE_BLOCK) ));

    public static final Block LEAD_ORE = registerBlock("lead_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.STONE).strength(2.25f,2.75f).
                    requiresTool() ));
    public static final Block DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.DEEPSLATE).strength(4.0f,2.75f).
                    requiresTool() ));
    public static final Block RAW_LEAD_BLOCK = registerBlock("raw_lead_block", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(5.0f,10.0f).
                    requiresTool() ));
    public static final Block LEAD_BLOCK = registerBlock("lead_block", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.COPPER).strength(5.0f,10.0f).
                    requiresTool() ));

    public static final Block PLATED_BLOCK = registerBlock("plated_block", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(3.0f,6.0f).
                    requiresTool() ));
    public static final Block PLATED_RAILING = registerBlock("plated_railing", false,
            new FenceBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(3.0f,6.0f).
                    requiresTool() ));
    public static final Block PLATED_GRATE = registerBlock("plated_grate", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(3.0f,6.0f).
                    requiresTool().nonOpaque() ));
    public static final Block PLATED_GRATE_STAIRS = registerBlock("plated_grate_stairs", false,
            new StairsBlock(ModBlocks.PLATED_GRATE.getDefaultState(),
                    FabricBlockSettings.copyOf(ModBlocks.PLATED_GRATE) ));
    public static final Block PLATED_GRATE_SLAB = registerBlock("plated_grate_slab", false,
            new SlabBlock(FabricBlockSettings.copyOf(PLATED_GRATE) ));

    public static final Block GRIMSTONE = registerBlock("grimstone", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.BASALT).strength(4.0f,6.0f).
                    requiresTool() ));
    public static final Block GRIMSTONE_STAIRS = registerBlock("grimstone_stairs", false,
            new StairsBlock(ModBlocks.GRIMSTONE.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE) ));
    public static final Block GRIMSTONE_SLAB = registerBlock("grimstone_slab", false,
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE) ));
    public static final Block GRIMSTONE_BRICKS = registerBlock("grimstone_bricks", false,
            new Block(FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE) ));
    public static final Block GRIMSTONE_BRICK_STAIRS = registerBlock("grimstone_brick_stairs", false,
            new StairsBlock(ModBlocks.GRIMSTONE_BRICKS.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE_BRICKS) ));
    public static final Block GRIMSTONE_BRICK_SLAB = registerBlock("grimstone_brick_slab", false,
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE_BRICKS) ));
    public static final Block GRIMSTONE_TILES = registerBlock("grimstone_tiles", false,
            new Block(FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE) ));
    public static final Block GRIMSTONE_TILE_STAIRS = registerBlock("grimstone_tile_stairs", false,
            new StairsBlock(ModBlocks.GRIMSTONE_TILES.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE_TILES) ));
    public static final Block GRIMSTONE_TILE_SLAB = registerBlock("grimstone_tile_slab", false,
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE_TILES) ));
    public static final Block POLISHED_GRIMSTONE = registerBlock("polished_grimstone", false,
            new Block(FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE) ));
    public static final Block POLISHED_GRIMSTONE_STAIRS = registerBlock("polished_grimstone_stairs", false,
            new StairsBlock(ModBlocks.POLISHED_GRIMSTONE.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.POLISHED_GRIMSTONE) ));
    public static final Block POLISHED_GRIMSTONE_SLAB = registerBlock("polished_grimstone_slab", false,
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.POLISHED_GRIMSTONE) ));
    public static final Block POLISHED_GRIMSTONE_BRICKS = registerBlock("polished_grimstone_bricks", false,
            new Block(FabricBlockSettings.copyOf(ModBlocks.GRIMSTONE) ));
    public static final Block POLISHED_GRIMSTONE_BRICK_STAIRS = registerBlock("polished_grimstone_brick_stairs", false,
            new StairsBlock(ModBlocks.POLISHED_GRIMSTONE_BRICKS.getDefaultState(),FabricBlockSettings.copyOf(ModBlocks.POLISHED_GRIMSTONE_BRICKS) ));
    public static final Block POLISHED_GRIMSTONE_BRICK_SLAB = registerBlock("polished_grimstone_brick_slab", false,
            new SlabBlock(FabricBlockSettings.copyOf(ModBlocks.POLISHED_GRIMSTONE_BRICKS) ));

    public static final Block TUNGSTEN_ORE = registerBlock("tungsten_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.STONE).strength(2.25f,2.75f).
                    requiresTool() ));
    public static final Block DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.DEEPSLATE).strength(4.0f,2.75f).
                    requiresTool() ));
    public static final Block NETHER_TUNGSTEN_ORE = registerBlock("nether_tungsten_ore", false,
            new ExperienceDroppingBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.NETHER_ORE).strength(3.0f,3.0f).
                    requiresTool(), UniformIntProvider.create(1,2) ));
    public static final Block RAW_TUNGSTEN_BLOCK = registerBlock("raw_tungsten_block", true,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.ANCIENT_DEBRIS).strength(5.0f,10.0f).
                    requiresTool() ));
    public static final Block TUNGSTEN_BLOCK = registerBlock("tungsten_block", true,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.NETHERITE).strength(5.0f,10.0f).
                    requiresTool() ));

    public static final Block MELTING_POT = registerBlock("melting_pot", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(5.0f,10.0f).
                    requiresTool().nonOpaque().solidBlock(ModBlocks::never)));
    public static final Block LEAD_PIPE = registerBlock("lead_pipe", false,
            new ConnectingPipe(FabricBlockSettings.create().sounds(BlockSoundGroup.COPPER).strength(5.0f,10.0f).
                    requiresTool().nonOpaque() ));
    public static final Block COPPER_CHANNEL = registerBlock("copper_channel", false,
            new ChannelBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.COPPER).strength(5.0f,10.0f).
                    requiresTool().nonOpaque() ));
    //Sep
    public static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
    //Sep
    private static Block registerBlock(String name, Boolean fireproof, Block block) {
        registerBlockItem(name, block, fireproof);
        return Registry.register(Registries.BLOCK, new Identifier(MeltingPoint.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block, Boolean fireproof){
        if (fireproof) {
            return Registry.register(Registries.ITEM, new Identifier(MeltingPoint.MOD_ID, name),
                    new BlockItem(block, new FabricItemSettings().fireproof()));
        } else {
            return Registry.register(Registries.ITEM, new Identifier(MeltingPoint.MOD_ID, name),
                    new BlockItem(block, new FabricItemSettings()));
        }
    }
    public static void registerModBlocks(){
        MeltingPoint.LOGGER.debug("Registering ModBlocks for " + MeltingPoint.MOD_ID);
    }
}
