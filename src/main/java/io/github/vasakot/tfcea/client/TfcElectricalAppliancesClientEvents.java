package io.github.vasakot.tfcea.client;

import io.github.vasakot.tfcea.client.screen.RefrigeratorScreen;
import io.github.vasakot.tfcea.common.container.TfcElectricalAppliancesContainers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class TfcElectricalAppliancesClientEvents {
    public static void init() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(TfcElectricalAppliancesClientEvents::clientSetup);
    }

    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(TfcElectricalAppliancesContainers.REFRIGERATOR_CONTAINER.get(), RefrigeratorScreen::new);
        });
    }
}
