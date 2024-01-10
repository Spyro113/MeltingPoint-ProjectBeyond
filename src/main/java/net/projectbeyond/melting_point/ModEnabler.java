package net.projectbeyond.melting_point;

import net.projectbeyond.melting_point.block.ModBlocks;
import net.projectbeyond.melting_point.item.ModItems;
import net.projectbeyond.melting_point.world.gen.ModWorldGeneration;

public class ModEnabler {

    //Metals
    static boolean mainCodeEnabled = false;

    public static void enableModsMainCode() {
        if (!mainCodeEnabled) {
            System.out.println(MeltingPoint.MOD_ID + " Melting Point has been enabled");
            mainCodeEnabled = true;

            //ModConfiguredFeatures.registerConfiguredFeatures();

            ModItems.registerModItems();
            ModBlocks.registerModBlocks();

            ModWorldGeneration.generateModWorldGen();

        }else{
            System.out.println(MeltingPoint.MOD_ID + " Attempted to enable Melting Point features multiple times");
        }
    }

}
