package io.github.vasakot.tfcea;

import com.mojang.logging.LogUtils;
import io.github.vasakot.tfcea.client.TfcElectricalAppliancesClientEvents;
import io.github.vasakot.tfcea.common.block.TfcElectricalAppliancesBlocks;
import io.github.vasakot.tfcea.common.blockentities.TfcElectricalAppliancesBlocksEntities;
import io.github.vasakot.tfcea.common.container.TfcElectricalAppliancesContainers;
import io.github.vasakot.tfcea.common.item.TfcElectricalAppliancesItems;
import io.github.vasakot.tfcea.common.tabs.TfcElectricalAppliancesCreativeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Mod(TfcElectricalAppliances.MOD_ID)
public class TfcElectricalAppliances {
    public static final String MOD_ID = "tfcea";
    public static final String MOD_NAME = "Tfc Electrical Appliances";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TfcElectricalAppliances() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TfcElectricalAppliancesItems.ITEMS.register(eventBus);
        TfcElectricalAppliancesBlocks.BLOCKS.register(eventBus);
        TfcElectricalAppliancesBlocksEntities.BLOCK_ENTITIES.register(eventBus);
        TfcElectricalAppliancesContainers.CONTAINERS.register(eventBus);
        TfcElectricalAppliancesCreativeTabs.TABS.register(eventBus);

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            TfcElectricalAppliancesClientEvents.init();
        }
    }
}
