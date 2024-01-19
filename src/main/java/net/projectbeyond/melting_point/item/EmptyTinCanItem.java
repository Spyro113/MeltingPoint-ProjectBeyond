package net.projectbeyond.melting_point.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.projectbeyond.melting_point.util.ModTags;

public class EmptyTinCanItem extends Item {
    public EmptyTinCanItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, player, hand);
        ItemStack itemstack = ar.getValue();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        canFood(player);
        return ar;
    }
    public static void canFood (Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity livEntity ? livEntity.getOffHandStack() : ItemStack.EMPTY).isIn(ModTags.Items.CANNABLE) ) {
            if (entity instanceof PlayerEntity player) {
                ItemStack stack = setCannedFoodData(entity);
                stack.setCount(1);
                player.getInventory().insertStack(stack);
            }
            if (entity instanceof PlayerEntity player) {
                ItemStack stacktoremove = (entity instanceof LivingEntity livEntity ? livEntity.getOffHandStack() : ItemStack.EMPTY);
                player.getInventory().remove(p -> stacktoremove.getItem() == p.getItem(), 1, player.getInventory());
            }
            if (entity instanceof PlayerEntity player) {
                ItemStack stack = new ItemStack(Items.BOWL);
                stack.setCount(1);
                player.getInventory().insertStack(stack);
            }
        }
    }
    public static ItemStack setCannedFoodData(Entity entity) {
        if (entity == null)
            return ItemStack.EMPTY;
        ItemStack CannedFoodInstance = ItemStack.EMPTY;
        CannedFoodInstance = new ItemStack(ModItems.CANNED_FOOD);
        CannedFoodInstance.setCustomName(Text.literal(("Canned Food " + ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffHandStack() : ItemStack.EMPTY).toHoverableText().getString()))));
        CannedFoodInstance.getOrCreateNbt().putString("FoodID", (Registries.ITEM.getKey((entity instanceof LivingEntity _livEnt ? _livEnt.getOffHandStack() : ItemStack.EMPTY).getItem()).toString()));
        return CannedFoodInstance;
    }
}
