package io.github.vasakot.tfcea.common.container;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import io.github.vasakot.tfcea.common.blockentities.TfcElectricalAppliancesBlocksEntities;
import net.dries007.tfc.common.blockentities.InventoryBlockEntity;
import net.dries007.tfc.common.container.BlockEntityContainer;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TfcElectricalAppliancesContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(Registries.MENU, TfcElectricalAppliances.MOD_ID);
    public static final RegistryObject<MenuType<RefrigeratorContainer>> REFRIGERATOR_CONTAINER = TfcElectricalAppliancesContainers.registerBlock("refrigerator", TfcElectricalAppliancesBlocksEntities.REFRIGERATOR_BLOCK, (BlockEntityContainer.Factory<RefrigeratorBlockEntity, RefrigeratorContainer>) RefrigeratorContainer::create);

    private static <T extends InventoryBlockEntity<?>, C extends BlockEntityContainer<T>> RegistryObject<MenuType<C>> registerBlock(String name, Supplier<BlockEntityType<T>> type, BlockEntityContainer.Factory<T, C> factory) {
        return RegistrationHelpers.registerBlockEntityContainer(CONTAINERS, name, type, factory);
    }
}