package com.crescentine.trajanscore;

import com.crescentine.trajanscore.basetank.BaseATEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class ATShootEvent extends Event {
    private final BaseATEntity at;

    public ATShootEvent(BaseATEntity at) {
        this.at = at;
    }

    public BaseATEntity getAT() {
        return at;
    }

    public static void register() {
        MinecraftForge.EVENT_BUS.register(ATShootEvent.class);
    }
}