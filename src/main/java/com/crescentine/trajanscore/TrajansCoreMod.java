package com.crescentine.trajanscore;

import com.crescentine.trajanscore.block.TankModBlockEntities;
import com.crescentine.trajanscore.block.engine_fabricator.EngineFabricatorRenderer;
import com.crescentine.trajanscore.block.platingpress.PlatingPressRenderer;
import com.crescentine.trajanscore.block.steelmanufacturer.SteelManufacturerRenderer;
import com.crescentine.trajanscore.block.turretfactory.TurretFactoryRenderer;
import com.crescentine.trajanscore.container.TankModContainers;
import com.crescentine.trajanscore.item.TankModItems;
import com.crescentine.trajanscore.recipe.ModRecipes;
import com.crescentine.trajanscore.screen.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("trajanscore")
public class TrajansCoreMod {
    public static final String MOD_ID = "trajanscore";
    public TrajansCoreMod() {
        GeckoLib.initialize();

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        TankModItems.ITEMS.register(eventBus);
        TankModItems.BLOCKS.register(eventBus);
        eventBus.addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
        TankModContainers.register(eventBus);
        TankModBlockEntities.register(eventBus);
        ModRecipes.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MenuScreens.register(TankModContainers.CRAFTER_CONTAINER.get(), CrafterScreen::new);
        MenuScreens.register(TankModContainers.ENGINE_FABRICATOR_CONTAINER.get(), EngineFabricatorScreen::new);
        MenuScreens.register(TankModContainers.STEEL_MANUFACTURER_CONTAINER.get(), SteelManufacturerScreen::new);
        MenuScreens.register(TankModContainers.PLATING_PRESS_CONTAINER.get(), PlatingPressScreen::new);
        MenuScreens.register(TankModContainers.TURRET_FACTORY_CONTAINER.get(), TurretFactoryScreen::new);

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @OnlyIn(Dist.CLIENT)
        @SubscribeEvent
        public static void registerRenderers(final FMLClientSetupEvent event) {
            BlockEntityRenderers.register(TankModBlockEntities.PLATING_PRESS.get(), PlatingPressRenderer::new);
            BlockEntityRenderers.register(TankModBlockEntities.TURRET_FACTORY.get(), TurretFactoryRenderer::new);
            BlockEntityRenderers.register(TankModBlockEntities.STEEL_MANUFACTURER.get(), SteelManufacturerRenderer::new);
            BlockEntityRenderers.register(TankModBlockEntities.ENGINE_FABRICATOR_BLOCK_ENTITY.get(), EngineFabricatorRenderer::new);

        }
    }
}
