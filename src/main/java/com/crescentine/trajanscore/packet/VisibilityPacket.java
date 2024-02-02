package com.crescentine.trajanscore.packet;

import com.crescentine.trajanscore.basetank.BaseATEntity;
import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;


public class VisibilityPacket {

    public VisibilityPacket() {
    }

    public void writePacketData(FriendlyByteBuf buf) {
    }

    public static VisibilityPacket decode(FriendlyByteBuf buffer) {
        return new VisibilityPacket();
    }

    public static void handle(VisibilityPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player player = context.getSender();
            if (player == null || !player.isAlive()) return;
            if (player.getVehicle() instanceof BaseTankEntity) {
                BaseTankEntity Tank = (BaseTankEntity) player.getVehicle();
                Tank.setVisibility(!Tank.getVisibility());
        }

        });
        context.setPacketHandled(true);
    }
}
