package com.crescentine.trajanscore.packet;

import com.crescentine.trajanscore.TrajansCoreMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class TrajansCoreNetwork {

    public static final String NETWORK_VERSION = "0.1.0";
    private static int channel_id = 0;

    public static final SimpleChannel TANK = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TrajansCoreMod.MOD_ID, "tank"), () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

    public static final SimpleChannel FUEL_REMAINING = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TrajansCoreMod.MOD_ID, "fuel_remaining"), () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

    public static void init() {
        FUEL_REMAINING.registerMessage(++channel_id, FuelRemainingPacket.class, FuelRemainingPacket::writePacketData, FuelRemainingPacket::decode, FuelRemainingPacket::handle);
        TANK.registerMessage(++channel_id, TankPacket.class, TankPacket::writePacketData, TankPacket::decode, TankPacket::handle);
    }
}