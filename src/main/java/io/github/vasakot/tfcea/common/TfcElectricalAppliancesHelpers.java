package io.github.vasakot.tfcea.common;

import io.github.vasakot.tfcea.TfcElectricalAppliances;
import net.minecraft.resources.ResourceLocation;

public class TfcElectricalAppliancesHelpers {
    public static ResourceLocation identifier(String id)
    {
        return new ResourceLocation(TfcElectricalAppliances.MOD_ID, id);
    }
}
