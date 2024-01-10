package net.projectbeyond.melting_point.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.projectbeyond.melting_point.MeltingPoint;
import net.projectbeyond.melting_point.block.ModBlocks;

public class ModItems {
    //Sep
    public static final Item RAW_TIN = registerItem( "raw_tin",
            new Item(new FabricItemSettings()));
    public static final Item TIN_PLATE = registerItem( "tin_plate",
            new Item(new FabricItemSettings()));
    //Sep
    public static final Item BRONZE_INGOT = registerItem( "bronze_ingot",
            new Item(new FabricItemSettings()));
    public static final Item BRONZE_NUGGET = registerItem( "bronze_nugget",
            new Item(new FabricItemSettings()));
    //Sep
    public static final Item RAW_LEAD = registerItem( "raw_lead",
            new Item(new FabricItemSettings()));
    public static final Item LEAD_INGOT = registerItem( "lead_ingot",
            new Item(new FabricItemSettings()));
    public static final Item LEAD_NUGGET = registerItem( "lead_nugget",
            new Item(new FabricItemSettings()));

    public static final Item RAW_TUNGSTEN = registerItem( "raw_tungsten",
            new Item(new FabricItemSettings().fireproof()));
    public static final Item TUNGSTEN_INGOT = registerItem( "tungsten_ingot",
            new Item(new FabricItemSettings().fireproof()));
    public static final Item TUNGSTEN_NUGGET = registerItem( "tungsten_nugget",
            new Item(new FabricItemSettings().fireproof()));
    //Sep
    private static void addItemsToBuildingBlocksTab(FabricItemGroupEntries entries){
        entries.add(ModBlocks.TIN_BLOCK);
        entries.add(ModBlocks.BRONZE_BLOCK);
        entries.add(ModBlocks.AGED_BRONZE_BLOCK);
        entries.add(ModBlocks.ANCIENT_BRONZE_BLOCK);
        entries.add(ModBlocks.RAW_LEAD_BLOCK);
        entries.add(ModBlocks.LEAD_BLOCK);
        entries.add(ModBlocks.RAW_TUNGSTEN_BLOCK);
        entries.add(ModBlocks.TUNGSTEN_BLOCK);
    }
    private static void addItemsToIngredientTab(FabricItemGroupEntries entries){
        entries.add(RAW_TIN);
        entries.add(TIN_PLATE);
        entries.add(BRONZE_INGOT);
        entries.add(BRONZE_NUGGET);
        entries.add(RAW_LEAD);
        entries.add(LEAD_INGOT);
        entries.add(LEAD_NUGGET);
        entries.add(RAW_TUNGSTEN);
        entries.add(TUNGSTEN_INGOT);
        entries.add(TUNGSTEN_NUGGET);
    }
    private static void addItemsToNaturalTab(FabricItemGroupEntries entries){
        entries.add(ModBlocks.TIN_ORE);
        entries.add(ModBlocks.DEEPSLATE_TIN_ORE);
        entries.add(ModBlocks.LEAD_ORE);
        entries.add(ModBlocks.DEEPSLATE_LEAD_ORE);
        entries.add(ModBlocks.TUNGSTEN_ORE);
        entries.add(ModBlocks.DEEPSLATE_TUNGSTEN_ORE);
        entries.add(ModBlocks.NETHER_TUNGSTEN_ORE);
    }
    private static void addItemsToFunctionalTab(FabricItemGroupEntries entries){
        entries.add(ModBlocks.LEAD_BLOCK);
        entries.add(ModBlocks.MELTING_POT);
        entries.add(ModBlocks.LEAD_PIPE);
    }
    //Sep
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MeltingPoint.MOD_ID, name), item);
    }
    public static void registerModItems(){
        MeltingPoint.LOGGER.debug("Registering Mod Items for " + MeltingPoint.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItems::addItemsToBuildingBlocksTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemsToNaturalTab);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItems::addItemsToFunctionalTab);
    }
}
