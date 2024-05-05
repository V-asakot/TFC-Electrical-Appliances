package io.github.vasakot.tfcea.client.screen;

import io.github.vasakot.tfcea.common.blockentities.RefrigeratorBlockEntity;
import io.github.vasakot.tfcea.common.container.RefrigeratorContainer;
import net.dries007.tfc.client.screen.BlockEntityScreen;
import net.dries007.tfc.util.Helpers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RefrigeratorScreen extends BlockEntityScreen<RefrigeratorBlockEntity, RefrigeratorContainer> {

    public static final ResourceLocation BACKGROUND = Helpers.identifier("textures/gui/refrigerator.png");

    public RefrigeratorScreen(RefrigeratorContainer container, Inventory playerInventory, Component name) {
        super(container, playerInventory, name, BACKGROUND);
    }
}
