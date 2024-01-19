package net.projectbeyond.melting_point.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Degradable;

public interface Aging extends Degradable<Aging.AgingLevel> {
    public static final Supplier<BiMap<Block, Block>> AGING_LEVEL_INCREASES =
            Suppliers.memoize(() ->
                    ((ImmutableBiMap.Builder)
                    ((ImmutableBiMap.Builder)ImmutableBiMap.builder().put
                    (ModBlocks.BRONZE_BLOCK, ModBlocks.AGED_BRONZE_BLOCK)).put
                    (ModBlocks.AGED_BRONZE_BLOCK, ModBlocks.ANCIENT_BRONZE_BLOCK)).build());

    public static final Supplier<BiMap<Block, Block>> AGING_LEVEL_DECREASES = Suppliers.memoize(() -> AGING_LEVEL_INCREASES.get().inverse());

    public static Optional<Block> getDecreasedAgingBlock(Block block) {
        return Optional.ofNullable((Block) AGING_LEVEL_DECREASES.get().get(block));
    }

    public static Block getUnaffectedAgingBlock(Block block) {
        Block block2 = block;
        Block block3 = (Block) AGING_LEVEL_DECREASES.get().get(block2);
        while (block3 != null) {
            block2 = block3;
            block3 = (Block) AGING_LEVEL_DECREASES.get().get(block2);
        }
        return block2;
    }

    public static Optional<BlockState> getDecreasedAgingState(BlockState state) {
        return Aging.getDecreasedAgingBlock(state.getBlock()).map(block -> block.getStateWithProperties(state));
    }

    public static Optional<Block> getIncreasedAgingBlock(Block block) {
        return Optional.ofNullable((Block) AGING_LEVEL_INCREASES.get().get(block));
    }

    public static BlockState getUnaffectedAgingState(BlockState state) {
        return Aging.getUnaffectedAgingBlock(state.getBlock()).getStateWithProperties(state);
    }

    @Override
    default public Optional<BlockState> getDegradationResult(BlockState state) {
        return Aging.getIncreasedAgingBlock(state.getBlock()).map(block -> block.getStateWithProperties(state));
    }

    @Override
    default public float getDegradationChanceMultiplier() {
        if (this.getDegradationLevel() == AgingLevel.UNAFFECTED) {
            return 0.325f;
        }
        return 0.125f;
    }
    public static enum AgingLevel {
        UNAFFECTED,
        AGED,
        ANCIENT;

    }
}
