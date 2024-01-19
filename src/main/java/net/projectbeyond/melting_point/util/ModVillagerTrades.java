package net.projectbeyond.melting_point.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.projectbeyond.melting_point.item.ModItems;

public class ModVillagerTrades {
    public static void registerModVillagerTrades()  {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 1,
                factories -> {
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 4),
                         new ItemStack(ModItems.TIN_PLATE, 6),
                         12, 8, 0.1f));
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 2),
                         new ItemStack(ModItems.TIN_PLATE, 4),
                         12, 8, 0.075f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.ARMORER, 2,
                factories -> {
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(ModItems.BRONZE_INGOT, 4),
                         new ItemStack(Items.EMERALD, 1),
                         8, 16, 0.05f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 1,
                factories -> {
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 3),
                         new ItemStack(ModItems.TIN_PLATE, 5),
                         12, 8, 0.075f));
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 4),
                         new ItemStack(ModItems.TIN_PLATE, 8),
                         12, 8, 0.05f));
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 1),
                         new ItemStack(ModItems.BRONZE_SHOVEL, 1),
                         6, 10, 0.15f));
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 2),
                         new ItemStack(ModItems.BRONZE_PICKAXE, 1),
                         6, 10, 0.15f));
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 1),
                         new ItemStack(ModItems.BRONZE_AXE, 1),
                         6, 10, 0.15f));
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 1),
                         new ItemStack(ModItems.BRONZE_HOE, 1),
                         6, 10, 0.15f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.BRONZE_INGOT, 3),
                            new ItemStack(Items.EMERALD, 1),
                            8, 16, 0.075f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 1,
                factories -> {
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 3),
                         new ItemStack(ModItems.TIN_PLATE, 5),
                         12, 8, 0.075f));
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 4),
                         new ItemStack(ModItems.TIN_PLATE, 8),
                         12, 8, 0.05f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.BRONZE_INGOT, 3),
                            new ItemStack(Items.EMERALD, 1),
                            8, 16, 0.075f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.BUTCHER, 1,
                factories -> {
                        factories.add((entity, random) -> new TradeOffer(
                         new ItemStack(Items.EMERALD, 6),
                         new ItemStack(ModItems.EMPTY_TIN_CAN, 12),
                         8, 5, 0.1f));
                });
    }
}
