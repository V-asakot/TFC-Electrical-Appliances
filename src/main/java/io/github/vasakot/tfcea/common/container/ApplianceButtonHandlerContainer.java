package io.github.vasakot.tfcea.common.container;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Nullable;

public interface ApplianceButtonHandlerContainer {
    void onButtonPress(int var1, @Nullable CompoundTag var2);
}
