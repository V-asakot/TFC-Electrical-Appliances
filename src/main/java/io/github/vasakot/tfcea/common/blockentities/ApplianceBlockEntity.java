package io.github.vasakot.tfcea.common.blockentities;

import io.github.vasakot.tfcea.common.capabilities.DelegateEnergyStorage;
import net.dries007.tfc.common.blockentities.TickableInventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApplianceBlockEntity<C extends IItemHandlerModifiable&INBTSerializable<CompoundTag>&DelegateEnergyStorage> extends TickableInventoryBlockEntity<C> {

    private boolean isTurnedOn;
    private boolean isActive;

    public final int energyTickConsumption;
    private LazyOptional<IEnergyStorage> lazyEnergyStorage = LazyOptional.empty();


    public ApplianceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, InventoryFactory<C> inventoryFactory, Component defaultName, boolean isTurnedOn, int activityConsumption) {
        super(type, pos, state, inventoryFactory, defaultName);
        this.isTurnedOn = isTurnedOn;
        this.isActive = false;
        this.energyTickConsumption = activityConsumption;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY){
            return lazyEnergyStorage.cast();
        }
        return super.getCapability(cap, side);
    }

    protected void toggleAppliance()
    {
        if(isTurnedOn){
            turnOff();
        }else{
            turnOn();
        }
    }

    protected void turnOn(){
        isTurnedOn = true;
    }

    protected void turnOff(){
        isTurnedOn = false;
    }

    public boolean isTurnedOn(){
        return isTurnedOn;
    }

    public boolean isActive(){
        return isActive;
    }

    public void setActivity(boolean isActive){
        this.isActive = isActive;
    }

    public int getEnergy(){
        return inventory.getEnergyStored();
    }

    public int getMaxEnergy(){
        return inventory.getMaxEnergyStored();
    }

    public void consumeEnergyForTicks(int ticks){
        inventory.consumeEnergy(energyTickConsumption * ticks);
    }

    @Override
    public void saveAdditional(CompoundTag nbt)
    {
        nbt.putBoolean("isTurnedOn", isTurnedOn);
        super.saveAdditional(nbt);
    }

    @Override
    public void onLoadAdditional()
    {
        lazyEnergyStorage = LazyOptional.of(inventory::getEnergyStorage);
    }

    @Override
    public void loadAdditional(CompoundTag nbt)
    {
        isTurnedOn = nbt.getBoolean("isTurnedOn");
        super.loadAdditional(nbt);
    }
}
