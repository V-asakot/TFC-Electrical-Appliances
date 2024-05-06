package io.github.vasakot.tfcea.client.screen;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.client.screen.button.RefrigeratorTurnOnButton;
import io.github.vasakot.tfcea.common.TfcElectricalAppliancesHelpers;
import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import io.github.vasakot.tfcea.common.container.RefrigeratorContainer;

import net.dries007.tfc.client.screen.BlockEntityScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RefrigeratorScreen extends BlockEntityScreen<RefrigeratorBlockEntity, RefrigeratorContainer> {

    public static final ResourceLocation BACKGROUND = TfcElectricalAppliancesHelpers.identifier("textures/gui/refrigerator.png");
    private static final Component TURN_OFF = Component.translatable(String.format("tooltip.%s.turn_off", TfcElectricalAppliances.MOD_ID));
    private static final Component TURN_ON = Component.translatable(String.format("tooltip.%s.turn_on", TfcElectricalAppliances.MOD_ID));

    public RefrigeratorScreen(RefrigeratorContainer container, Inventory playerInventory, Component name) {
        super(container, playerInventory, name, BACKGROUND);
        imageWidth += 20;
    }

    public void init()
    {
        super.init();
        addRenderableWidget(new RefrigeratorTurnOnButton(blockEntity, getGuiLeft(), getGuiTop(), blockEntity.isTurnedOn() ? TURN_OFF : TURN_ON));
    }
}
