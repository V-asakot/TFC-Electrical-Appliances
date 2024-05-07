package io.github.vasakot.tfcea.common.container;

import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import io.github.vasakot.tfcea.common.item.TfcElectricalAppliancesFoodTraits;
import net.dries007.tfc.common.capabilities.Capabilities;
import net.dries007.tfc.common.capabilities.food.FoodCapability;
import net.dries007.tfc.common.container.BlockEntityContainer;
import net.dries007.tfc.common.container.CallbackSlot;
import net.dries007.tfc.common.container.RestrictedChestContainer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class RefrigeratorContainer extends BlockEntityContainer<RefrigeratorBlockEntity> implements  ApplianceButtonHandlerContainer {

    public static RefrigeratorContainer create(RefrigeratorBlockEntity refrigerator, Inventory playerInventory, int windowId) {
        return new RefrigeratorContainer(windowId, refrigerator).init(playerInventory);
    }

    protected RefrigeratorContainer(int windowId, RefrigeratorBlockEntity blockEntity) {
        super(TfcElectricalAppliancesContainers.REFRIGERATOR_CONTAINER.get(), windowId, blockEntity);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        Slot fromSlot = getSlot(index);
        ItemStack fromStack = fromSlot.getItem();

        if (fromStack.getCount() <= 0)
            fromSlot.set(ItemStack.EMPTY);

        if (!fromSlot.hasItem())
            return ItemStack.EMPTY;



        if (index < 27) {
            // move from block entity inventory
            FoodCapability.removeTrait(fromStack, TfcElectricalAppliancesFoodTraits.REFRIGERATING);
            if (!moveItemStackTo(fromStack, 27, 62, false))
                return ItemStack.EMPTY;
        } else if (index < 63) {
            //  move from player's inventory
            if (!moveItemStackTo(fromStack, 0, 26, false))
                return ItemStack.EMPTY;
        } else {
            return ItemStack.EMPTY;
        }

        ItemStack copyFromStack = fromStack.copy();
        fromSlot.setChanged();
        fromSlot.onTake(player, fromStack);

        return copyFromStack;
    }

    @Override
    public void onButtonPress(int var1, @Nullable CompoundTag var2) {
        blockEntity.toggleAppliance();
    }

    public int getEnergyStoredScaled() {
        return (int) (((float)  blockEntity.getEnergy() / (float) blockEntity.getMaxEnergy()) * 64);
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
