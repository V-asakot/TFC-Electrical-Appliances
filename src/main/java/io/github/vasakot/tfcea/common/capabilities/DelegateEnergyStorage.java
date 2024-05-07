package io.github.vasakot.tfcea.common.capabilities;

import net.minecraftforge.energy.IEnergyStorage;

public interface DelegateEnergyStorage extends IEnergyStorage {
    IEnergyStorage getEnergyStorage();
    @Override
    default int receiveEnergy(int amount, boolean b) {
        return getEnergyStorage().receiveEnergy(amount, b);
    }

    @Override
    default int extractEnergy(int amount, boolean b) {
        return getEnergyStorage().extractEnergy(amount, b);
    }

    default int consumeEnergy(int amount){
        return getEnergyStorage().extractEnergy(amount, false);
    }

    @Override
    default int getEnergyStored() {
        return getEnergyStorage().getEnergyStored();
    }

    @Override
    default int getMaxEnergyStored() {
        return getEnergyStorage().getMaxEnergyStored();
    }

    @Override
    default boolean canExtract() {
        return getEnergyStorage().canExtract();
    }

    @Override
    default boolean canReceive() {
        return getEnergyStorage().canReceive();
    }

}