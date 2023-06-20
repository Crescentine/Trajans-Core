package com.crescentine.trajanscore;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public class TankModClient {
    public static final String sync = "key.trajanscore.sync";
    public static final String shootKey = "key.trajanscore.shootKey";
    public static final String fuelRemaining = "key.trajanscore.fuelRemaining";

    public static final KeyMapping SYNC_TURRET_WITH_TANK = new KeyMapping(sync, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_CONTROL, KeyMapping.CATEGORY_MISC);
    public static final KeyMapping SHOOT_KEY = new KeyMapping(shootKey, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KeyMapping.CATEGORY_MISC);
    public static final KeyMapping FUEL_CHECK = new KeyMapping(fuelRemaining, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL, KeyMapping.CATEGORY_MISC);

    @Mod.EventBusSubscriber(modid = TrajansCoreMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(SYNC_TURRET_WITH_TANK);
            event.register(SHOOT_KEY);
            event.register(FUEL_CHECK);
        }
        @SubscribeEvent
        public static void registerGui(RegisterGuiOverlaysEvent event) {
            StatsOverlay overlay = new StatsOverlay();
            event.registerAboveAll("overlay", overlay);
        }
    }
}
