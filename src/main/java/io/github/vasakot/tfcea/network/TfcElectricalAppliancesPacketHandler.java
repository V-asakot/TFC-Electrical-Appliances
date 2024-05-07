package io.github.vasakot.tfcea.network;

import io.github.vasakot.tfcea.common.TfcElectricalAppliancesHelpers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class TfcElectricalAppliancesPacketHandler {
    private static final String VERSION = Integer.toString(1);
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(TfcElectricalAppliancesHelpers.identifier("network"), () -> VERSION, VERSION::equals, VERSION::equals);
    private static final MutableInt ID = new MutableInt(0);

    public static void send(PacketDistributor.PacketTarget target, Object message)
    {
        CHANNEL.send(target, message);
    }

    public static void init()
    {
        // Client -> Server
        register(ApplianceButtonPacket.class, ApplianceButtonPacket::encode, ApplianceButtonPacket::new, ApplianceButtonPacket::handle);
    }

    private static <T> void register(Class<T> cls, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, ServerPlayer> handler)
    {
        CHANNEL.registerMessage(ID.getAndIncrement(), cls, encoder, decoder, (packet, context) -> {
            context.get().setPacketHandled(true);
            context.get().enqueueWork(() -> handler.accept(packet, context.get().getSender()));
        });
    }
}
