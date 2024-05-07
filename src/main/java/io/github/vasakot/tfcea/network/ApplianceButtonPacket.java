package io.github.vasakot.tfcea.network;

import io.github.vasakot.tfcea.common.container.ApplianceButtonHandlerContainer;
import net.dries007.tfc.util.Helpers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public class ApplianceButtonPacket {
    private final int buttonID;
    @Nullable
    private final CompoundTag extraNBT;

    public ApplianceButtonPacket(int buttonID, @Nullable CompoundTag extraNBT)
    {
        this.buttonID = buttonID;
        this.extraNBT = extraNBT;
    }

    ApplianceButtonPacket(FriendlyByteBuf buffer)
    {
        buttonID = buffer.readVarInt();
        extraNBT = Helpers.decodeNullable(buffer, FriendlyByteBuf::readNbt);
    }

    void encode(FriendlyByteBuf buffer)
    {
        buffer.writeVarInt(buttonID);
        Helpers.encodeNullable(extraNBT, buffer, (nbt, buf) -> buf.writeNbt(nbt));
    }

    void handle(@Nullable ServerPlayer player)
    {
        if (player != null && player.containerMenu instanceof ApplianceButtonHandlerContainer handler)
        {
            handler.onButtonPress(buttonID, extraNBT);
        }
    }
}
