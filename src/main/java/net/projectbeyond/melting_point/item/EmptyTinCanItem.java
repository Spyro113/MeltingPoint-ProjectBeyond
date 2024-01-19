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

        canFood(player);
        if (!player.getAbilities().creativeMode){
            stack.decrement(1);
        }
        return ar;
    }
    public static void canFood (Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity livEntity ? livEntity.getOffHandStack() : ItemStack.EMPTY).isIn(ModTags.Items.CANNABLE) ) {
            if (entity instanceof PlayerEntity player) {
                ItemStack cannedfood = setCannedFoodData(entity);
                LivingEntity livingentity = (LivingEntity) entity;
                ItemStack food = livingentity.getOffHandStack();
                ItemStack bowl = new ItemStack(Items.BOWL);
                cannedfood.setCount(1);
                player.getInventory().insertStack(cannedfood);
                player.getInventory().remove(p -> food.getItem() == p.getItem(), 1, player.getInventory());
                bowl.setCount(1);
                player.getInventory().insertStack(bowl);
            }
        }
    }
    public static ItemStack setCannedFoodData(Entity entity) {
        if (entity == null)
            return ItemStack.EMPTY;
        ItemStack cannedfoodinstance = new ItemStack(ModItems.CANNED_FOOD);
        LivingEntity livingentity = (LivingEntity) entity;
        ItemStack offhandstack = livingentity.getOffHandStack();
        Identifier offhandidentifier = Registries.ITEM.getId(offhandstack.getItem());

        NbtCompound nbtTag = (entity instanceof LivingEntity ? offhandstack : ItemStack.EMPTY).getNbt();
        if (nbtTag != null) {
            cannedfoodinstance.setNbt(nbtTag.copy());
        }

        cannedfoodinstance.setCustomName(Text.literal(("Canned " + (offhandstack.getName().getString()))));
        cannedfoodinstance.getOrCreateNbt().put("FoodID", NbtString.of( (offhandidentifier.toString()) ));

        return cannedfoodinstance;
    }
}
