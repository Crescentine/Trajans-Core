package com.crescentine.trajanscore.packet;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class VisibilityPacket {
    public int key;
    public VisibilityPacket(int key) {
        this.key = key;
    }
    public VisibilityPacket(FriendlyByteBuf buffer) {
        key = buffer.readInt();
    }
    public static VisibilityPacket decode(FriendlyByteBuf buffer) {
        return new VisibilityPacket(buffer.readInt());
    }
    public void writePacketData(FriendlyByteBuf buf) {
        buf.writeInt(this.key);
    }
    public static void handle(VisibilityPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
                    Player player = context.getSender();
                    if (player == null || !player.isAlive()) return;
                    if (player.getVehicle() instanceof BaseTankEntity tank && tank.isAlive()) {
tank.toggleVisibility();
                    }
                }
        );
        context.setPacketHandled(true);
    }
}