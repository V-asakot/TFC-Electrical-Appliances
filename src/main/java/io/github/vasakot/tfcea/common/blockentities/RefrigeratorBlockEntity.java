package io.github.vasakot.tfcea.common.blockentities;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.common.capabilities.DelegateEnergyStorage;
import io.github.vasakot.tfcea.common.capabilities.EnergyStorageCallback;
import io.github.vasakot.tfcea.common.capabilities.InventoryConsumerEnergyStorage;
import io.github.vasakot.tfcea.common.container.RefrigeratorContainer;
import io.github.vasakot.tfcea.common.item.TfcElectricalAppliancesFoodTraits;
import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.capabilities.InventoryItemHandler;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefrigeratorBlockEntity extends ApplianceBlockEntity<RefrigeratorBlockEntity.RefrigeratorInventory> implements EnergyStorageCallback {
    public static final int SLOTS = 27;
    private static final Component NAME = Component.translatable(String.format("block.%s.refrigerator", TfcElectricalAppliances.MOD_ID));

    public RefrigeratorBlockEntity(BlockPos pos, BlockState state) {
        super(TfcElectricalAppliancesBlocksEntities.REFRIGERATOR_BLOCK.get(), pos, state, RefrigeratorInventory::new, NAME,true,20);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, RefrigeratorBlockEntity refrigerator)
    {
        refrigerator.checkForLastTickSync();
        if (level.getGameTime() % 2 == 0)
        {
            if(refrigerator.isTurnedOn() && refrigerator.isActive()){
                refrigerator.consumeEnergyForTicks(2);
                if(refrigerator.inventory.energyStorage.getEnergyStored() < refrigerator.activityTickConsumption){
                    refrigerator.setActivity(false);
                    refrigerator.removeRefrigeratorTraitFromInventory();
                }
            }
            else if(refrigerator.isTurnedOn() && !refrigerator.isActive()){
                if(refrigerator.inventory.energyStorage.getEnergyStored() >= refrigerator.activityTickConsumption){
                    refrigerator.setActivity(true);
                    refrigerator.applyRefrigeratorTraitToInventory();
                    refrigerator.consumeEnergyForTicks(2);
                }
            }

            refrigerator.markForSync();
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, Inventory inv, Player player) {
        return RefrigeratorContainer.create(this, inv, windowID);
    }

    @Override
    public void toggleAppliance() {
        if(isTurnedOn()){
            turnOff();
        }else{
            turnOn();
        }
        markForSync();
    }

    @Override
    public void ejectInventory() {
        turnOff();
        super.ejectInventory();
    }
    @Override
    public void energyLevelChanged()
    {
    }

    @Override
    protected void turnOff() {
        super.turnOff();
        removeRefrigeratorTraitFromInventory();
    }

    @Override
    protected void turnOn() {
        super.turnOn();
        applyRefrigeratorTraitToInventory();
    }

    private void applyRefrigeratorTraitToInventory(){
        inventory.applyRefrigeratorTraitToInventory();
    }

    private void removeRefrigeratorTraitFromInventory(){
        inventory.removeRefrigeratorTraitFromInventory();
    }

    public static class RefrigeratorInventory extends InventoryItemHandler implements DelegateEnergyStorage, INBTSerializable<CompoundTag> {
        private static final int CAPACITY = 10000;
        private static final int MAX_TRANSFER = 100;

        private final RefrigeratorBlockEntity refrigerator;
        private final InventoryConsumerEnergyStorage energyStorage;

        public RefrigeratorInventory(InventoryBlockEntity<RefrigeratorInventory> entity)
        {
            super(entity, SLOTS);
            refrigerator = (RefrigeratorBlockEntity) entity;
            energyStorage = new InventoryConsumerEnergyStorage(CAPACITY, MAX_TRANSFER, (EnergyStorageCallback) entity);
        }

        @Deprecated
        @Override
        public void setStackInSlot(int slot, ItemStack stack)
        {
            if(refrigerator.isTurnedOn() && refrigerator.isActive() && !FoodCapability.hasTrait(stack, TfcElectricalAppliancesFoodTraits.REFRIGERATING)){
                super.setStackInSlot(slot, FoodCapability.applyTrait(stack.copy(), TfcElectricalAppliancesFoodTraits.REFRIGERATING));
            }else{
                super.setStackInSlot(slot, stack.copy());
            }
        }

        @NotNull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return FoodCapability.removeTrait(super.extractItem(slot, amount, simulate).copy(), TfcElectricalAppliancesFoodTraits.REFRIGERATING);
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

        @Override
        public IEnergyStorage getEnergyStorage() {
            return energyStorage;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag compoundTag = super.serializeNBT();
            compoundTag.put("EnergyStorage", energyStorage.serializeNBT());
            return compoundTag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            super.deserializeNBT(nbt);
            energyStorage.deserializeNBT(nbt.get("EnergyStorage"));
        }

        public boolean isFoodStack(ItemStack stack){
            return !stack.isEmpty() && FoodCapability.get(stack) != null;
        }
    }
}
