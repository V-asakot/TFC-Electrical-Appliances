package io.github.vasakot.tfcea.common.blockentities;

import net.dries007.tfc.common.blockentities.TickableInventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandlerModifiable;

public class ApplianceBlockEntity<C extends IItemHandlerModifiable&INBTSerializable<CompoundTag>> extends TickableInventoryBlockEntity<C> {

    private boolean isTurnedOn;
    public ApplianceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, InventoryFactory<C> inventoryFactory, Component defaultName, boolean isTurnedOn) {
        super(type, pos, state, inventoryFactory, defaultName);
        this.isTurnedOn = isTurnedOn;
    }

    public void turnOn(){
        isTurnedOn = true;
    }

    public void turnOff(){
        isTurnedOn = false;
    }

    public boolean isTurnedOn(){
        return isTurnedOn;
    }

    public boolean isActive(){
        return isTurnedOn();
    }

    @Override
    public void saveAdditional(CompoundTag nbt)
    {
        nbt.putBoolean("isTurnedOn", isTurnedOn);
        super.saveAdditional(nbt);
    }

    @Override
    public void loadAdditional(CompoundTag nbt)
    {
        isTurnedOn = nbt.getBoolean("isTurnedOn");
        super.loadAdditional(nbt);
    }
}
