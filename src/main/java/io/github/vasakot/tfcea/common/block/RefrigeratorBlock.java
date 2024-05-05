package io.github.vasakot.tfcea.common.block;

import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RefrigeratorBlock extends FourWayFacingDeviceBlock {

    public RefrigeratorBlock(ExtendedProperties properties) {
        super(properties, InventoryRemoveBehavior.DROP);
        registerDefaultState(getStateDefinition().any());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!player.isShiftKeyDown()) {
            level.getBlockEntity(pos, getExtendedProperties().<RefrigeratorBlockEntity>blockEntity()).ifPresent(refrigerator -> {
                if (player instanceof ServerPlayer serverPlayer) {
                    Helpers.openScreen(serverPlayer, refrigerator, pos);
                }
            });
        }
        return InteractionResult.SUCCESS;
    }
}
