package net.projectbeyond.melting_point.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.*;
import net.minecraft.registry.Registries;
import net.minecraft.resource.NamespaceResourceManager;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.projectbeyond.melting_point.util.ModTags;

import java.util.Optional;

public class EmptyTinCanItem extends Item {
    public EmptyTinCanItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, player, hand);
        ItemStack stack = ar.getValue();

        canFood(player,stack);
        return ar;
    }
    public static void canFood (Entity entity, ItemStack stack) {
        if (entity == null)
            return;

        LivingEntity livingEntity = (LivingEntity) entity;
        ItemStack food = livingEntity.getOffHandStack();
        if (food.isIn(ModTags.Items.CANNABLE)) {
            if (entity instanceof PlayerEntity player) {
                ItemStack cannedFood = setCannedFoodData(entity);
                ItemStack bowl = new ItemStack(Items.BOWL);
                cannedFood.setCount(1);
                player.getInventory().insertStack(cannedFood);
                player.getInventory().remove(p -> food.getItem() == p.getItem(), 1, player.getInventory());
                bowl.setCount(1);
                player.getInventory().insertStack(bowl);
                if (!player.getAbilities().creativeMode){
                    stack.decrement(1);
                }
            }
        }
    }
    public static ItemStack setCannedFoodData(Entity entity) {
        if (entity == null)
            return ItemStack.EMPTY;
        ItemStack cannedFoodInstance = new ItemStack(ModItems.CANNED_FOOD);
        LivingEntity livingEntity = (LivingEntity) entity;
        ItemStack offHandStack = livingEntity.getOffHandStack();
        Identifier offHandIdentifier = Registries.ITEM.getId(offHandStack.getItem());
        NbtCompound nbtTag = (entity instanceof LivingEntity ? offHandStack : ItemStack.EMPTY).getNbt();

        if (nbtTag != null) {
            cannedFoodInstance.setNbt(nbtTag.copy());
        }
        if (offHandStack.hasCustomName()){
            cannedFoodInstance.getOrCreateNbt().put("FoodCustomName", NbtString.of( (offHandStack.getName().getString()) ));
        }
        cannedFoodInstance.setCustomName(Text.literal(("Canned " + (offHandStack.getName().getString()) )));
        cannedFoodInstance.getOrCreateNbt().put("FoodID", NbtString.of( (offHandIdentifier.toString()) ));

        return cannedFoodInstance;
    }
}
