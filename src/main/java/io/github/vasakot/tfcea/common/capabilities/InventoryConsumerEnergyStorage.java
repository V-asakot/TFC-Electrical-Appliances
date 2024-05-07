package io.github.vasakot.tfcea.common.capabilities;

import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import net.minecraftforge.energy.EnergyStorage;

public class InventoryConsumerEnergyStorage extends EnergyStorage {
    private final EnergyStorageCallback callback;
    public InventoryConsumerEnergyStorage(int capacity, int maxTransfer, EnergyStorageCallback callback) {
        super(capacity, maxTransfer);
        this.callback = callback;
    }

    @Override
    public int extractEnergy(int amount, boolean b) {
        int consumed = super.extractEnergy(amount, b);
        if(consumed > 0){
            energyLevelChanged();
        }
        return consumed;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int received = super.receiveEnergy(maxReceive, simulate);
        if(received > 0){
            energyLevelChanged();
        }
        return received;
    }

    public void energyLevelChanged(){

    }
}
