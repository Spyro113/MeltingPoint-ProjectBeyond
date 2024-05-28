package net.projectbeyond.melting_point.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Map;

public class ChannelBlock extends Block {

    public static final EnumProperty<Direction.Axis> AXIS;
    public static final IntProperty POWER = Properties.POWER;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES;


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d offset = state.getModelOffset(world, pos);
        return (switch (state.get(AXIS)) {
            case X -> Block.createCuboidShape(0, 5, 5, 16, 11, 11);
            case Y -> Block.createCuboidShape(5, 0, 5, 11, 16, 11);
            case Z -> Block.createCuboidShape(5, 5, 0, 11, 11, 16);
        }).offset(offset.x, offset.y, offset.z);
    }
    
    public ChannelBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)
                this.stateManager.getDefaultState())
                .with(AXIS, Direction.Axis.Y))
                .with(POWER, 0))
                .with(NORTH, false))
                .with(EAST, false))
                .with(SOUTH, false))
                .with(WEST, false))
                .with(UP, false))
                .with(DOWN, false));
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return changeRotation(state, rotation);
    }

    public static BlockState changeRotation(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> {
                return switch ((Direction.Axis) state.get(AXIS)) {
                    case X -> (BlockState) state.with(AXIS, Direction.Axis.Z);
                    case Z -> (BlockState) state.with(AXIS, Direction.Axis.X);
                    default -> state;
                };
            }
            default -> {
                return state;
            }
        }
    }
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState) super.getPlacementState(ctx).with(AXIS, ctx.getSide().getAxis());
    }

//======================================================================================================================

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        this.updatePower(state, world, pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        this.updatePower(state, world, pos);
    }



    public void updatePower(BlockState state, World world, BlockPos pos) {
        if (!world.isClient) {
            int power = world.getReceivedRedstonePower(pos);
            world.setBlockState(pos, state.with(POWER, MathHelper.clamp(power, 0, 15)), 1 | 2 | 4);
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }
    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if ((state.get(POWER) != 0) && (
         ChannelBlock.getDirectionFromAxis(state) == direction ||
         ChannelBlock.getDirectionFromAxis(state).getOpposite() == direction)) {
            return Math.max(0, state.get(POWER) - 1);
        }
        return 0;
    }
/*
    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {

        Direction axis = ChannelBlock.getDirectionFromAxis(state);
        BlockPos adjPos = pos.offset(axis);
        BlockState adjState = world.getBlockState(adjPos);
        BlockPos adjPos2 = pos.offset(axis.getOpposite());
        BlockState adjState2 = world.getBlockState(adjPos2);

        if (isShapeFullCube(adjState, world, adjPos)){
            return  state.getWeakRedstonePower(world, pos, direction);
        }
        if (isShapeFullCube(adjState2, world, adjPos2)){
            return  state.getWeakRedstonePower(world, pos, direction);
        }
        return 0;
    }
*/


    public static Direction getDirectionFromAxis (BlockState state) {

        switch (state.get(AXIS)) {
            case Y: {
                return Direction.UP;
            }
            case X: {
                return Direction.WEST;
            }
            case Z: {
                return Direction.NORTH;
            }
        }
        return null;
    }

//======================================================================================================================

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
        builder.add(POWER);
        builder.add(NORTH, EAST, WEST, SOUTH, UP, DOWN);
    }

    static {
        AXIS = Properties.AXIS;
        NORTH = Properties.NORTH;
        EAST = Properties.EAST;
        SOUTH = Properties.SOUTH;
        WEST = Properties.WEST;
        UP = Properties.UP;
        DOWN = Properties.DOWN;
        FACING_PROPERTIES = ImmutableMap.copyOf((Map)Util.make(Maps.newEnumMap(Direction.class), (directions) -> {
            directions.put(Direction.NORTH, NORTH);
            directions.put(Direction.EAST, EAST);
            directions.put(Direction.SOUTH, SOUTH);
            directions.put(Direction.WEST, WEST);
            directions.put(Direction.UP, UP);
            directions.put(Direction.DOWN, DOWN);
        }));
    }





}
