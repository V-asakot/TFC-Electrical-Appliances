package io.github.vasakot.tfcea.common.item;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class TfcElectricalAppliancesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TfcElectricalAppliances.MOD_ID);
    public static final RegistryObject<Item> PORTABLE_REFRIGERATOR_ITEM = ITEMS.register("portable_refrigerator",
            () -> new PortableRefrigeratorItem(new Item.Properties().stacksTo(1))
    );
    public static final RegistryObject<Item> REFRIGERATOR_BLOCK_ITEM = ITEMS.register("refrigerator",
            () -> new RefrigeratorBlockItem(new Item.Properties().stacksTo(1)));
}
