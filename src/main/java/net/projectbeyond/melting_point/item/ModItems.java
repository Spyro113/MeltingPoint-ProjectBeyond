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

public class ModItems {
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
    private static void addItemsToIngredientTab(FabricItemGroupEntries entries){
        entries.add(RAW_LEAD);
        entries.add(LEAD_INGOT);
        entries.add(LEAD_NUGGET);
        entries.add(RAW_TUNGSTEN);
        entries.add(TUNGSTEN_INGOT);
        entries.add(TUNGSTEN_NUGGET);
    }
    //Sep
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MeltingPoint.MOD_ID, name), item);
    }
    public static void registerModItems(){
        MeltingPoint.LOGGER.debug("Registering Mod Items for " + MeltingPoint.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTab);
    }
}
