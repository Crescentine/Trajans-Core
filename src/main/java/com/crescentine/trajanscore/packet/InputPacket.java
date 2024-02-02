package com.crescentine.trajanscore.packet;

import com.crescentine.trajanscore.basetank.BaseATEntity;
import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class InputPacket {
    public boolean inputUp;
    public boolean inputDown;
    public boolean inputLeft;
    public boolean inputRight;

    BaseTankEntity entity;

    public InputPacket(BaseTankEntity base, boolean inputUp, boolean inputDown, boolean inputLeft, boolean inputRight) {
        this.inputUp = inputUp;
        this.inputDown = inputDown;
        this.inputLeft = inputLeft;
        this.inputRight = inputRight;
        this.entity = base;
    }

    public InputPacket(FriendlyByteBuf buffer) {
        this.inputUp = buffer.readBoolean();
        this.inputDown = buffer.readBoolean();
        this.inputLeft = buffer.readBoolean();
        this.inputRight = buffer.readBoolean();
    }

    public void writePacketData(FriendlyByteBuf buffer) {
        buffer.writeBoolean(this.inputUp);
        buffer.writeBoolean(this.inputDown);
        buffer.writeBoolean(this.inputLeft);
        buffer.writeBoolean(this.inputRight);
    }

    public static InputPacket decode(FriendlyByteBuf buffer) {
        return new InputPacket(buffer);
    }

    public static void handle(InputPacket message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
        });

        context.setPacketHandled(true);
    }
}

