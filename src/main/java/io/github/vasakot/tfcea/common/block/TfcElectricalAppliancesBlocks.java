package io.github.vasakot.tfcea.common.block;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TfcElectricalAppliancesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TfcElectricalAppliances.MOD_ID);
    public static final RegistryObject<Block> REFRIGERATOR_BLOCK = BLOCKS.register("refrigerator",
            () -> new RefrigeratorBlock(BlockBehaviour.Properties.of()
                    .strength(3.5f)
                    .requiresCorrectToolForDrops()
            ));
}
