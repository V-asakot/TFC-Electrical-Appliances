package io.github.vasakot.tfcea.common.block;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import io.github.vasakot.tfcea.common.blockentities.TfcElectricalAppliancesBlocksEntities;
import io.github.vasakot.tfcea.common.item.TfcElectricalAppliancesItems;
import net.dries007.tfc.common.blockentities.TFCBlockEntities;
import net.dries007.tfc.common.blocks.ExtendedProperties;

import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;

public class TfcElectricalAppliancesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TfcElectricalAppliances.MOD_ID);

    public static final RegistryObject<Block> REFRIGERATOR_BLOCK = register("refrigerator",
            () -> new RefrigeratorBlock(ExtendedProperties.of()
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .blockEntity(TfcElectricalAppliancesBlocksEntities.REFRIGERATOR_BLOCK)
                    .serverTicks(RefrigeratorBlockEntity::serverTick)
            ));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return RegistrationHelpers.registerBlock(BLOCKS, TfcElectricalAppliancesItems.ITEMS, name, blockSupplier, blockItemFactory);
    }
}
