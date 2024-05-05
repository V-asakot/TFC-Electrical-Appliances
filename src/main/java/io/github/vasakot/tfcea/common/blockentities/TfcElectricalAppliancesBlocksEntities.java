package io.github.vasakot.tfcea.common.blockentities;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.common.block.TfcElectricalAppliancesBlocks;
import net.dries007.tfc.util.registry.RegistrationHelpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class TfcElectricalAppliancesBlocksEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TfcElectricalAppliances.MOD_ID);
    public static final RegistryObject<BlockEntityType<RefrigeratorBlockEntity>> REFRIGERATOR_BLOCK = register("refrigerator", RefrigeratorBlockEntity::new, TfcElectricalAppliancesBlocks.REFRIGERATOR_BLOCK);

    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block> block) {
        return RegistrationHelpers.register(BLOCK_ENTITIES, name, factory, block);
    }

}
