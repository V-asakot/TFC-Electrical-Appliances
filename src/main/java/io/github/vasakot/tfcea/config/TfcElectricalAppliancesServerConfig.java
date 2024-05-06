package io.github.vasakot.tfcea.config;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import io.github.vasakot.tfcea.common.item.TfcElectricalAppliancesFoodTraits;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TfcElectricalAppliancesServerConfig {

    public final Map<TfcElectricalAppliancesFoodTraits.Default, ForgeConfigSpec.DoubleValue> foodTraits;

    TfcElectricalAppliancesServerConfig(ForgeConfigSpec.Builder innerBuilder) {
        Function<String, ForgeConfigSpec.Builder> builder =
                name -> innerBuilder.translation(TfcElectricalAppliances.MOD_ID + ".config.server." + name);

        innerBuilder.push("general");

        foodTraits = new HashMap<>();
        Arrays.stream(TfcElectricalAppliancesFoodTraits.Default.values()).forEach(trait ->
                foodTraits.put(trait, builder.apply("trait" + trait.getCapitalizedName() + "Modifier").comment("The modifier for the '" + trait.getCapitalizedName() + "' food trait. Values less than 1 extend food lifetime, values greater than one decrease it. A value of zero stops decay.")
                        .defineInRange("trait" + trait.getCapitalizedName() + "Modifier", trait.getMod(), 0, Double.MAX_VALUE))
        );
    }
}
