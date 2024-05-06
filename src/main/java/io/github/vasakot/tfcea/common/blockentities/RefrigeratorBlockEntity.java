package io.github.vasakot.tfcea.common.blockentities;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.common.container.RefrigeratorContainer;
import io.github.vasakot.tfcea.common.item.TfcElectricalAppliancesFoodTraits;
import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.capabilities.InventoryItemHandler;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefrigeratorBlockEntity extends ApplianceBlockEntity<RefrigeratorBlockEntity.RefrigeratorInventory> {
    public static final int SLOTS = 27;
    private static final Component NAME = Component.translatable(String.format("block.%s.refrigerator", TfcElectricalAppliances.MOD_ID));

    public RefrigeratorBlockEntity(BlockPos pos, BlockState state) {
        super(TfcElectricalAppliancesBlocksEntities.REFRIGERATOR_BLOCK.get(), pos, state, RefrigeratorInventory::new, NAME,true);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, RefrigeratorBlockEntity refrigerator)
    {
        refrigerator.checkForLastTickSync();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory inv, Player player) {
        return RefrigeratorContainer.create(this, inv, windowID);
    }

    @Override
    public void turnOff() {
        super.turnOff();
        removeRefrigeratorTraitFromInventory();
    }

    public void turnOn() {
        super.turnOn();
        applyRefrigeratorTraitToInventory();
    }

    @Override
    public void ejectInventory() {
        turnOff();
        super.ejectInventory();
    }

    private void applyRefrigeratorTraitToInventory(){
        inventory.applyRefrigeratorTraitToInventory();
    }

    private void removeRefrigeratorTraitFromInventory(){
        inventory.removeRefrigeratorTraitFromInventory();
    }

    public static class RefrigeratorInventory extends InventoryItemHandler implements INBTSerializable<CompoundTag> {
        private final RefrigeratorBlockEntity refrigerator;

        public RefrigeratorInventory(InventoryBlockEntity<?> entity) {
            super(entity, SLOTS);
            refrigerator = (RefrigeratorBlockEntity) entity;
        }

        @Deprecated
        @Override
        public void setStackInSlot(int slot, ItemStack stack)
        {
            if(refrigerator.isActive() && !FoodCapability.hasTrait(stack, TfcElectricalAppliancesFoodTraits.REFRIGERATING)){
                super.setStackInSlot(slot, FoodCapability.applyTrait(stack, TfcElectricalAppliancesFoodTraits.REFRIGERATING));
            }else{
                super.setStackInSlot(slot, stack);
            }
        }

        @NotNull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return FoodCapability.removeTrait(super.extractItem(slot, amount, simulate), TfcElectricalAppliancesFoodTraits.REFRIGERATING);
        }

        public void applyRefrigeratorTraitToInventory(){
            for (int i = 0; i < SLOTS; i++)
            {
                super.setStackInSlot(i, FoodCapability.applyTrait(super.getStackInSlot(i).copy(), TfcElectricalAppliancesFoodTraits.REFRIGERATING));
            }
        }

        public void removeRefrigeratorTraitFromInventory(){
            for (int i = 0; i < SLOTS; i++)
            {
                super.setStackInSlot(i, FoodCapability.removeTrait(super.getStackInSlot(i).copy(), TfcElectricalAppliancesFoodTraits.REFRIGERATING));
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return isFoodStack(stack) && super.isItemValid(slot, stack);
        }

        private boolean isFoodStack(ItemStack stack){
            return !stack.isEmpty() && FoodCapability.get(stack) != null;
        }
    }
}
