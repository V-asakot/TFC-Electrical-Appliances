package io.github.vasakot.tfcea.common.container;

import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.container.BlockEntityContainer;
import net.dries007.tfc.common.container.CallbackSlot;
import net.dries007.tfc.common.container.RestrictedChestContainer;
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
            int rows = 3;
            for (int row = 0; row < rows; ++row)
            {
                for (int col = 0; col < 9; ++col)
                {
                    addSlot(new CallbackSlot(blockEntity, handler, col + row * 9, 8 + col * 18, 18 + row * 18));
                }
            }
        });
    }
}
