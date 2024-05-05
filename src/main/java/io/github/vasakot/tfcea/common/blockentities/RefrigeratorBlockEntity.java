package io.github.vasakot.tfcea.common.blockentities;

import io.github.vasakot.tfcea.common.container.RefrigeratorContainer;
import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.capabilities.InventoryItemHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefrigeratorBlockEntity extends InventoryBlockEntity<RefrigeratorBlockEntity.RefrigeratorInventory> {
    public static final int SLOTS = 27;
    private static final Component NAME = Component.translatable("block.tfcea.refrigerator");

    public RefrigeratorBlockEntity(BlockPos pos, BlockState state) {
        super(TfcElectricalAppliancesBlocksEntities.REFRIGERATOR_BLOCK.get(), pos, state, RefrigeratorInventory::new, NAME);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory inv, Player player) {
        return RefrigeratorContainer.create(this, inv, windowID);
    }

    public static class RefrigeratorInventory extends InventoryItemHandler implements INBTSerializable<CompoundTag> {
        private final RefrigeratorBlockEntity refrigerator;

        public RefrigeratorInventory(InventoryBlockEntity<?> entity) {
            super(entity, SLOTS);
            refrigerator = (RefrigeratorBlockEntity) entity;
        }

        @NotNull
        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            //todo: добавить тег заморозки
            return super.insertItem(slot, stack, simulate);
        }

        @NotNull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            //todo: снять тег заморозки
            return super.extractItem(slot, amount, simulate);
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return super.isItemValid(slot, stack);
        }
    }
}
