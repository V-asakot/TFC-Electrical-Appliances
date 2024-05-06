package io.github.vasakot.tfcea.common.item;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.common.TfcElectricalAppliancesHelpers;
import io.github.vasakot.tfcea.config.TfcElectricalAppliancesConfig;
import net.dries007.tfc.common.capabilities.food.FoodTrait;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class TfcElectricalAppliancesFoodTraits {
    public enum Default
    {
        REFRIGERATING(0.125f);

        private final float mod;
        private final String name;

        Default(float mod)
        {
            this.mod = mod;
            this.name = name().toLowerCase(Locale.ROOT);
        }

        public String getName()
        {
            return name;
        }

        public String getCapitalizedName()
        {
            return name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
        }

        public float getMod()
        {
            return mod;
        }
    }
    public static final FoodTrait REFRIGERATING = register(Default.REFRIGERATING);

    private static FoodTrait register(TfcElectricalAppliancesFoodTraits.Default trait)
    {
        return FoodTrait.register(TfcElectricalAppliancesHelpers.identifier(trait.name), new WrappedFoodTrait(()
                -> TfcElectricalAppliancesConfig.SERVER.foodTraits.get(trait).get().floatValue(),
                String.format("tooltip.%s.food_trait.", TfcElectricalAppliances.MOD_ID) + trait.name));
    }

    private static class WrappedFoodTrait extends FoodTrait
    {
        private final Supplier<Float> decayModifier;
        @Nullable
        private final String translationKey;

        public WrappedFoodTrait(Supplier<Float> decayModifier, @Nullable String translationKey)
        {
            super(1f, translationKey);
            this.decayModifier = decayModifier;
            this.translationKey = translationKey;
        }

        @Override
        public float getDecayModifier()
        {
            return decayModifier.get();
        }

        @Override
        public void addTooltipInfo(ItemStack stack, List<Component> text)
        {
            if (this.translationKey != null)
            {
                MutableComponent component = Component.translatable(this.translationKey);
                if (this.decayModifier.get() > 1.0F)
                {
                    component.withStyle(ChatFormatting.RED);
                }

                text.add(component);
            }
        }
    }
}
