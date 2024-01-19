package net.projectbeyond.melting_point.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.projectbeyond.melting_point.MeltingPoint;

public class ModTags {
    public static class Blocks{


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(MeltingPoint.MOD_ID, name));
        }

    }

    public static class Items{
        public static final TagKey<Item> CANNABLE = createTag("cannable");
        public static final TagKey<Item> IRON_LEAD_INTERCHANGEABLE_NUGGETS = createTag("iron_lead_interchangeable_nuggets");
        public static final TagKey<Item> IRON_LEAD_INTERCHANGEABLE_INGOTS = createTag("iron_lead_interchangeable_ingots");
        public static final TagKey<Item> IRON_LEAD_INTERCHANGEABLE_BLOCKS = createTag("iron_lead_interchangeable_blocks");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MeltingPoint.MOD_ID, name));
        }

    }
}
