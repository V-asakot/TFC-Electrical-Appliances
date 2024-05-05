package io.github.vasakot.tfcea.common.tabs;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.common.item.TfcElectricalAppliancesItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = TfcElectricalAppliances.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TfcElectricalAppliancesCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TfcElectricalAppliances.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TFC_ELECTRICAL_APPLIANCES_TAB = TABS.register("tfcea_creative_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(TfcElectricalAppliancesItems.PORTABLE_REFRIGERATOR_ITEM.get()))
                    .title(Component.translatable("itemGroup.tfcea_creative_tab"))
                    .displayItems(TfcElectricalAppliancesCreativeTabs::fillTfceaTab)
                    .build());

    public static void fillTfceaTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output out) {
        out.accept(TfcElectricalAppliancesItems.PORTABLE_REFRIGERATOR_ITEM.get());
        out.accept(TfcElectricalAppliancesItems.REFRIGERATOR_BLOCK_ITEM.get());
    }

    @SubscribeEvent
    public static void onBuildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(TfcElectricalAppliancesItems.REFRIGERATOR_BLOCK_ITEM);
            event.accept(TfcElectricalAppliancesItems.PORTABLE_REFRIGERATOR_ITEM);
        }
    }
}
