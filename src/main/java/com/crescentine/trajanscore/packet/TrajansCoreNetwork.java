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
/*
    public static final SimpleChannel FUEL_REMAINING = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TrajansCoreMod.MOD_ID, "fuel_remaining"), () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

    public static final SimpleChannel INPUT = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TrajansCoreMod.MOD_ID, "input"), () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

    public static final SimpleChannel MOVEMENT = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TrajansCoreMod.MOD_ID, "movement"), () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));




    public static final SimpleChannel IS_INVISIBLE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TrajansCoreMod.MOD_ID, "is_invisible"), () -> NETWORK_VERSION,
            version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

*/
    public static void init() {
        TANK.registerMessage(++channel_id, FuelRemainingPacket.class, FuelRemainingPacket::writePacketData, FuelRemainingPacket::decode, FuelRemainingPacket::handle);
        TANK.registerMessage(++channel_id, TankPacket.class, TankPacket::writePacketData, TankPacket::decode, TankPacket::handle);
        TANK.registerMessage(++channel_id, InputPacket.class, InputPacket::writePacketData, InputPacket::decode, InputPacket::handle);
        TANK.registerMessage(++channel_id, VisibilityPacket.class, VisibilityPacket::writePacketData, VisibilityPacket::decode, VisibilityPacket::handle);



    }

}