package io.github.vasakot.tfcea.common.item;

import io.github.vasakot.tfcea.common.block.TfcElectricalAppliancesBlocks;
import net.minecraft.world.item.BlockItem;

@SuppressWarnings("unused")
public class RefrigeratorBlockItem extends BlockItem {

    public RefrigeratorBlockItem(Properties properties) {
        super(TfcElectricalAppliancesBlocks.REFRIGERATOR_BLOCK.get(), properties);
    }
}
