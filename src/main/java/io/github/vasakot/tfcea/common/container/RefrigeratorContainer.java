package io.github.vasakot.tfcea.common.container;

import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.container.BlockEntityContainer;
import net.dries007.tfc.common.container.CallbackSlot;
import net.minecraft.world.entity.player.Inventory;

public class RefrigeratorContainer extends BlockEntityContainer<RefrigeratorBlockEntity> {

    public static RefrigeratorContainer create(RefrigeratorBlockEntity refrigerator, Inventory playerInventory, int windowId) {
        return new RefrigeratorContainer(windowId, refrigerator).init(playerInventory);
    }

    protected RefrigeratorContainer(int windowId, RefrigeratorBlockEntity blockEntity) {
        super(TfcElectricalAppliancesContainers.REFRIGERATOR_CONTAINER.get(), windowId, blockEntity);
    }

    @Override
    protected void addContainerSlots() {
        blockEntity.getCapability(Capabilities.ITEM).ifPresent(handler -> {
            addSlot(new CallbackSlot(blockEntity, handler, 0, 62, 19));
            addSlot(new CallbackSlot(blockEntity, handler, 1, 80, 19));
            addSlot(new CallbackSlot(blockEntity, handler, 2, 98, 19));
            addSlot(new CallbackSlot(blockEntity, handler, 3, 62, 37));
            addSlot(new CallbackSlot(blockEntity, handler, 4, 80, 37));
            addSlot(new CallbackSlot(blockEntity, handler, 5, 98, 37));
            addSlot(new CallbackSlot(blockEntity, handler, 6, 62, 55));
            addSlot(new CallbackSlot(blockEntity, handler, 7, 80, 55));
            addSlot(new CallbackSlot(blockEntity, handler, 8, 98, 55));
        });
    }
}
