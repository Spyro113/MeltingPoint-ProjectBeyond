package net.projectbeyond.melting_point.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
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
                    requiresTool()));
    public static final Block DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.DEEPSLATE).strength(4.0f,2.75f).
                    requiresTool()));
    public static final Block TIN_BLOCK = registerBlock("tin_block", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(5.0f,10.0f).
                    requiresTool()));

    public static final Block BRONZE_BLOCK = registerBlock("bronze_block", false,
            new AgingBlock(Aging.AgingLevel.UNAFFECTED, FabricBlockSettings.create().sounds(BlockSoundGroup.COPPER).strength(5.0f,10.0f).
                    requiresTool()));
    public static final Block AGED_BRONZE_BLOCK = registerBlock("aged_bronze_block", false,
            new AgingBlock(Aging.AgingLevel.AGED,
                    FabricBlockSettings.copyOf(ModBlocks.BRONZE_BLOCK)));
    public static final Block ANCIENT_BRONZE_BLOCK = registerBlock("ancient_bronze_block", false,
            new AgingBlock(Aging.AgingLevel.ANCIENT,
                    FabricBlockSettings.copyOf(ModBlocks.BRONZE_BLOCK)));

    public static final Block LEAD_ORE = registerBlock("lead_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.STONE).strength(2.25f,2.75f).
                    requiresTool()));
    public static final Block DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.DEEPSLATE).strength(4.0f,2.75f).
                    requiresTool()));
    public static final Block RAW_LEAD_BLOCK = registerBlock("raw_lead_block", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(5.0f,10.0f).
                    requiresTool()));
    public static final Block LEAD_BLOCK = registerBlock("lead_block", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.COPPER).strength(5.0f,10.0f).
                    requiresTool()));

    public static final Block TUNGSTEN_ORE = registerBlock("tungsten_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.STONE).strength(2.25f,2.75f).
                    requiresTool()));
    public static final Block DEEPSLATE_TUNGSTEN_ORE = registerBlock("deepslate_tungsten_ore", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.DEEPSLATE).strength(4.0f,2.75f).
                    requiresTool()));
    public static final Block NETHER_TUNGSTEN_ORE = registerBlock("nether_tungsten_ore", false,
            new ExperienceDroppingBlock(FabricBlockSettings.create().sounds(BlockSoundGroup.NETHER_ORE).strength(3.0f,3.0f).
                    requiresTool(), UniformIntProvider.create(1,2)));
    public static final Block RAW_TUNGSTEN_BLOCK = registerBlock("raw_tungsten_block", true,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.ANCIENT_DEBRIS).strength(5.0f,10.0f).
                    requiresTool()));
    public static final Block TUNGSTEN_BLOCK = registerBlock("tungsten_block", true,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.NETHERITE).strength(5.0f,10.0f).
                    requiresTool()));

    public static final Block MELTING_POT = registerBlock("melting_pot", false,
            new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.METAL).strength(5.0f,10.0f).
                    requiresTool().nonOpaque().solidBlock(ModBlocks::never)));
    public static final Block LEAD_PIPE = registerBlock("lead_pipe", false,
            new ConnectingPipe(FabricBlockSettings.create().sounds(BlockSoundGroup.COPPER).strength(5.0f,10.0f).
                    requiresTool().nonOpaque()));
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
