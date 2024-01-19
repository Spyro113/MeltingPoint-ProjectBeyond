package net.projectbeyond.melting_point.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CannedFoodItem extends Item {
    public CannedFoodItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getValue();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        openCannedFood(entity, itemstack);
        return ar;
    }
    public static void openCannedFood (Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;
        if (entity instanceof PlayerEntity _playerHasItem ? _playerHasItem.getInventory().contains(new ItemStack(Items.BOWL)) : false) {
            itemstack.setCount((int) (itemstack.getCount() - 1));
            if (entity instanceof PlayerEntity _player) {
                ItemStack _stktoremove = new ItemStack(Items.BOWL);
                _player.getInventory().remove(p -> _stktoremove.getItem() == p.getItem(), 1, _player.getInventory());
            }
            if (entity instanceof PlayerEntity _player) {
                ItemStack _setstack = new ItemStack(Registries.ITEM.get(new Identifier(((itemstack.getOrCreateNbt().getString("FoodID"))).toLowerCase(java.util.Locale.ENGLISH))));
                _setstack.setCount(1);
                _player.getInventory().insertStack(_setstack);
            }
        }
    }



}
