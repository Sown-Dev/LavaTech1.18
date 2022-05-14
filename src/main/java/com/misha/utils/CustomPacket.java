package com.misha.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CustomPacket {
    int index=0;
    CompoundTag data;

    public CustomPacket(FriendlyByteBuf packetBuffer) {
        index = packetBuffer.readShort();
        data = packetBuffer.readNbt();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
      //  context.enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientHandlers.handle(this)));
        context.setPacketHandled(true);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeShort(index);
        buf.writeNbt(data);
    }
}
