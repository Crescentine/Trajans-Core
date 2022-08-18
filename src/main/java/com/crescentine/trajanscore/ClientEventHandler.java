package com.crescentine.trajanscore;

import net.minecraftforge.common.MinecraftForge;

public class ClientEventHandler {
    public static void setup() {
        MinecraftForge.EVENT_BUS.register(new StatsOverlay());
    }
}
