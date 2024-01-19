package net.projectbeyond.melting_point.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CannedFoodItem extends Item {
    public CannedFoodItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, player, hand);
        ItemStack stack = ar.getValue();

        if (canOpenCannedFood(player) && !world.isClient){
            openCannedFood(player, stack);
            if (!player.getAbilities().creativeMode){
                stack.decrement(1);
            }
        }
        return ar;
    }
    private static boolean canOpenCannedFood (Entity entity) {
        return entity instanceof PlayerEntity hasItem && hasItem.getInventory().contains(new ItemStack(Items.BOWL));
    }
    public static void openCannedFood (Entity entity, ItemStack stack) {
        if (entity instanceof PlayerEntity player) {
            ItemStack bowl = new ItemStack(Items.BOWL);
            ItemStack food = new ItemStack(Registries.ITEM.get(new Identifier((stack.getOrCreateNbt().getString("FoodID")).toLowerCase(java.util.Locale.ENGLISH))));
            NbtCompound nbtTag = stack.getNbt();

            player.getInventory().remove(p -> bowl.getItem() == p.getItem(), 1, player.getInventory());
            food.setCount(1);

            food.setNbt(nbtTag.copy());
            if (food.getSubNbt("FoodCustomName") != null) {
                food.setCustomName( Text.literal( (food.getSubNbt("FoodCustomName").toString()) ) );
                food.removeSubNbt("FoodCustomName");
            } else {
                food.removeCustomName();
                food.removeSubNbt("FoodCustomName");
            }
            food.removeSubNbt("FoodID");

            player.getInventory().insertStack(food);
        }
    }

}
