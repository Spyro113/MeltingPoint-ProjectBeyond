package net.projectbeyond.melting_point.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Map;

public class ConnectingPipe extends Block {
    public static final EnumProperty<Direction.Axis> AXIS;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES;

    public ConnectingPipe(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)
                this.stateManager.getDefaultState())
                .with(AXIS, Direction.Axis.Y))
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
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)state.get(AXIS)) {
                    case X:
                        return (BlockState)state.with(AXIS, Direction.Axis.Z);
                    case Z:
                        return (BlockState)state.with(AXIS, Direction.Axis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    public boolean canConnect(BlockState state, Direction direction) {
        Block block = state.getBlock();
        boolean isPipe = block instanceof ConnectingPipe;
        return isPipe;
    }
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockPos blockPos2 = blockPos.north();
        BlockPos blockPos3 = blockPos.east();
        BlockPos blockPos4 = blockPos.south();
        BlockPos blockPos5 = blockPos.west();
        BlockPos blockPos6 = blockPos.up();
        BlockPos blockPos7 = blockPos.down();
        BlockState blockState = blockView.getBlockState(blockPos2);
        BlockState blockState2 = blockView.getBlockState(blockPos3);
        BlockState blockState3 = blockView.getBlockState(blockPos4);
        BlockState blockState4 = blockView.getBlockState(blockPos5);
        BlockState blockState5 = blockView.getBlockState(blockPos6);
        BlockState blockState6 = blockView.getBlockState(blockPos7);
        return (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)
                super.getPlacementState(ctx)
                        .with(AXIS, ctx.getSide().getAxis()))
                        .with(NORTH, this.canConnect(blockState, Direction.SOUTH)))
                        .with(EAST, this.canConnect(blockState2, Direction.WEST)))
                        .with(SOUTH, this.canConnect(blockState3, Direction.NORTH)))
                        .with(WEST, this.canConnect(blockState4, Direction.EAST)))
                        .with(UP, this.canConnect(blockState5, Direction.DOWN)))
                        .with(DOWN, this.canConnect(blockState6, Direction.UP)));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }


    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return (BlockState)state.with((Property)FACING_PROPERTIES.get(direction), this.canConnect(neighborState, direction.getOpposite()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AXIS});
        builder.add(new Property[]{NORTH, EAST, WEST, SOUTH, UP, DOWN});
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
