package net.projectbeyond.melting_point.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.projectbeyond.melting_point.block.Aging;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class AgingBlock extends Block implements Aging {
        private final Aging.AgingLevel agingLevel;

    public AgingBlock(Aging.AgingLevel agingLevel, AbstractBlock.Settings settings) {
            super(settings);
            this.agingLevel = agingLevel;
        }
        @Override
        public void randomTick (BlockState state, ServerWorld world, BlockPos pos, Random random){
            this.tickDegradation(state, world, pos, random);
        }
        @Override
        public boolean hasRandomTicks (BlockState state){
            return Aging.getIncreasedAgingBlock(state.getBlock()).isPresent();
        }
        @Override
        public Aging.AgingLevel getDegradationLevel () {
            return this.agingLevel;
        }
    }

