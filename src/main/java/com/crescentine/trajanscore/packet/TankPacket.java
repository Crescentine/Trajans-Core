package com.crescentine.trajanscore.packet;

import com.crescentine.trajanscore.TankShootEvent;
import com.crescentine.trajanscore.basetank.BaseATEntity;
import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class TankPacket {
    public int key;

    public TankPacket(int key) {
        this.key = key;
    }
    public TankPacket(FriendlyByteBuf buffer) {
        key = buffer.readInt();
    }
    public static TankPacket decode(FriendlyByteBuf buffer) {
        return new TankPacket(buffer.readInt());
    }
    public void writePacketData(FriendlyByteBuf buf) {
        buf.writeInt(this.key);
    }
    public static void handle(TankPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
                    Player player = context.getSender();
                    if (player == null || !player.isAlive()) return;
                    if (player.getVehicle() instanceof BaseTankEntity) {
                        BaseTankEntity Tank = (BaseTankEntity) player.getVehicle();
                        if (Tank.getFuelAmount() != 0) {
                            Tank.shoot(player, Tank, player.level());
                        }


                    }
                    if (player.getVehicle() instanceof BaseATEntity) {
                        BaseATEntity AT = (BaseATEntity) player.getVehicle();
                        AT.shoot(player, AT, player.level());
                    }
                }
        );
        context.setPacketHandled(true);
    }
}