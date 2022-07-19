package com.crescentine.trajanscore;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

public class TankModClient {
    public static final String startMoving = "key.trajanscore.startMoving";
    public static final String shootKey = "key.trajanscore.shootKey";
    public static final String fuelRemaining = "key.trajanscore.fuelRemaining";

    public static final KeyMapping START_MOVING = new KeyMapping(startMoving, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_W, KeyMapping.CATEGORY_MISC);
    public static final KeyMapping SHOOT_KEY = new KeyMapping(shootKey, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KeyMapping.CATEGORY_MISC);
    public static final KeyMapping FUEL_CHECK = new KeyMapping(fuelRemaining, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL, KeyMapping.CATEGORY_MISC);

    @Mod.EventBusSubscriber(modid = TrajansCoreMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(START_MOVING);
            event.register(SHOOT_KEY);
            event.register(FUEL_CHECK);
        }
    }
}
