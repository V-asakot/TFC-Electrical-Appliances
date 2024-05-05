package io.github.vasakot.tfcea.common.item;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Locale;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TfcElectricalAppliancesItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TfcElectricalAppliances.MOD_ID);
    public static final RegistryObject<Item> PORTABLE_REFRIGERATOR_ITEM = register("appliances/portable_refrigerator",
            () -> new PortableRefrigeratorItem(new Item.Properties().stacksTo(1))
    );
    public static final RegistryObject<Item> REFRIGERATOR_BLOCK_ITEM = register("appliances/refrigerator",
            () -> new RefrigeratorBlockItem(new Item.Properties().stacksTo(1)));

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}
