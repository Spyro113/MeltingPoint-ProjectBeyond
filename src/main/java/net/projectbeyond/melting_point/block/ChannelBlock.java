package net.projectbeyond.melting_point.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Map;

public class ChannelBlock extends Block{

    public static final EnumProperty<Direction.Axis> AXIS;
    public static final IntProperty POWER = Properties.POWER;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES;

    private boolean channelsGivePower = true;

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
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(POWER, 0)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false));
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return changeRotation(state, rotation);
    }

    public static BlockState changeRotation(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> {
                return switch (state.get(AXIS)) {
                    case X -> state.with(AXIS, Direction.Axis.Z);
                    case Z -> state.with(AXIS, Direction.Axis.X);
                    default -> state;
                };
            }
            default -> {
                return state;
            }
        }
    }
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(AXIS, ctx.getSide().getAxis());
    }

//======================================================================================================================

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        this.updatePower(state, world, pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        this.updatePower(state, world, pos);
        this.neighborBlockNeighborsUpdate(state, world, sourcePos);
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    public void neighborBlockNeighborsUpdate (BlockState state, World world, BlockPos pos) {
        Direction direction1 = getDirectionForFullBlockNeighbors(state, world, pos, getDirectionFromAxis(state));
        Direction direction2 = getDirectionForFullBlockNeighbors(state, world, pos, getDirectionFromAxis(state).getOpposite());
        if (direction1 != null) {
            world.updateNeighborsExcept(pos.offset(direction1), this, direction1.getOpposite());
        }
        if (direction2 != null) {
            world.updateNeighborsExcept(pos.offset(direction2), this, direction2.getOpposite());
        }
    }

    private Direction getDirectionForFullBlockNeighbors(BlockState state, World world, BlockPos pos, Direction direction){
        if (isShapeFullCube(state, world, pos.offset(direction))) {
            return ChannelBlock.getDirectionFromAxis(state);
        }
        return null;
    }
    public static boolean sendingPowerToPosition(BlockView world, BlockPos pos1, BlockPos pos2) {
        BlockState block = world.getBlockState(pos1);
        BlockState adjBlock = world.getBlockState(pos2);
        return block.get(POWER) > adjBlock.getStrongRedstonePower(world, pos2, ChannelBlock.getDirectionFromAxis(adjBlock));
    }

    public static boolean recivingPowerFromPosition(BlockView world, BlockPos pos1, BlockPos pos2) {
        BlockState block = world.getBlockState(pos1);
        BlockState adjBlock = world.getBlockState(pos2);
        return block.get(POWER) <= adjBlock.getStrongRedstonePower(world, pos2, ChannelBlock.getDirectionFromAxis(adjBlock));
    }

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

   /* public void updatePower(BlockState state, World world, BlockPos pos) {
        if (!world.isClient) {
            int power = world.getReceivedRedstonePower(pos);
            world.setBlockState(pos, state.with(POWER, MathHelper.clamp(power, 0, 15)), 1 | 2 | 4);
        }
    }*/


    private void updatePower(BlockState state, World world, BlockPos pos) {
        int power = this.getReceivedRedstonePower(state, world, pos);
        if (state.get(POWER) != power) {
            if (world.getBlockState(pos) == state) {
                world.setBlockState(pos, (BlockState)state.with(POWER, power), Block.NOTIFY_LISTENERS);
            }
            HashSet<BlockPos> set = Sets.newHashSet();
            set.add(pos);
            for (Direction direction : Direction.values()) {
                set.add(pos.offset(direction));
            }
            for (BlockPos blockPos : set) {
                world.updateNeighborsAlways(blockPos, this);
            }
        }
    }

    private int getReceivedRedstonePower(BlockState state,World world, BlockPos pos) {
        this.channelsGivePower = false;
        int power = world.getReceivedRedstonePower(pos);
        this.channelsGivePower = true;
        Direction[] directions = {getDirectionFromAxis(state), getDirectionFromAxis(state).getOpposite()};
        int powerStrength = 0;
        if (power < 15) {
            for (Direction direction : directions) {
                BlockPos blockPos = pos.offset(direction);
                BlockState blockState = world.getBlockState(blockPos);
                Block block = blockState.getBlock();
                boolean isChannel = block instanceof ChannelBlock;
                BlockState sourceBlockState = world.getBlockState(pos);
                Block sourceBlock = sourceBlockState.getBlock();
                boolean sourceIsChannel = sourceBlock instanceof ChannelBlock;

                //powerStrength = Math.max(powerStrength, this.increasePower(blockState));

                if ( (blockState.isSolidBlock(world, blockPos) || ((isChannel && sourceIsChannel) &&
                    (blockState.get(AXIS) == sourceBlockState.get(AXIS)) ) ) ) {
                    powerStrength = Math.max(powerStrength, this.increasePower(blockState));
                    continue;
                }
            }
        }
        return Math.max(power, powerStrength - 1);
    }

    private int increasePower(BlockState state) {
        return state.isOf(this) ? state.get(POWER) : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return this.channelsGivePower;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        Direction direction1 = ChannelBlock.getDirectionFromAxis(state);
        Direction direction2 = ChannelBlock.getDirectionFromAxis(state).getOpposite();
        if ( (state.get(POWER) != 0) && ((direction1 == direction) || (direction2 == direction)) ) {
            return Math.max(0, state.get(POWER) - 1);
        }
        return 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {

        if (!this.channelsGivePower) {
            return 0;
        }

        Direction axis = ChannelBlock.getDirectionFromAxis(state);
        BlockPos adjPos = pos.offset(axis);
        BlockState adjState = world.getBlockState(adjPos);
        BlockPos adjPos2 = pos.offset(axis.getOpposite());
        BlockState adjState2 = world.getBlockState(adjPos2);

        if (isShapeFullCube(adjState, world, adjPos) /*&& !recivingPowerFromPosition(world, pos, adjPos)*/ ){
            return  state.getWeakRedstonePower(world, pos, direction);
        }
        if (isShapeFullCube(adjState2, world, adjPos2) /*&& !recivingPowerFromPosition(world, pos, adjPos2)*/ ){
            return  state.getWeakRedstonePower(world, pos, direction);
        }
        return 0;
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
