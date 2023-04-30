package com.crescentine.trajanscore;

import com.crescentine.trajanscore.block.TankModBlockEntities;
import com.crescentine.trajanscore.block.engine_fabricator.EngineFabricatorRenderer;
import com.crescentine.trajanscore.block.platingpress.PlatingPressRenderer;
import com.crescentine.trajanscore.block.shellworkbench.ShellWorkbenchRenderer;
import com.crescentine.trajanscore.block.steelmanufacturer.SteelManufacturerRenderer;
import com.crescentine.trajanscore.block.turretfactory.TurretFactoryRenderer;
import com.crescentine.trajanscore.container.TankModContainers;
import com.crescentine.trajanscore.example_at.ExampleATRenderer;
import com.crescentine.trajanscore.example_tank.ExampleTankRenderer;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.packet.TrajansCoreNetwork;
import com.crescentine.trajanscore.recipe.ModRecipes;
import com.crescentine.trajanscore.screen.*;
import com.crescentine.trajanscore.tankshells.apcr.APCRShellRenderer;
import com.crescentine.trajanscore.tankshells.armorpiercing.ArmorPiercingShellRenderer;
import com.crescentine.trajanscore.tankshells.heat.HeatShellRenderer;
import com.crescentine.trajanscore.tankshells.highexplosive.HighExplosiveShellRenderer;
import com.crescentine.trajanscore.tankshells.standard.StandardShellRenderer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("trajanscore")
public class TrajansCoreMod {
    public static final String MOD_ID = "trajanscore";

    public TrajansCoreMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TrajansCoreConfig.SPEC, "trajanscore-config.toml");
        GeckoLib.initialize();
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        TrajansCoreItems.ITEMS.register(eventBus);
        TrajansCoreItems.BLOCKS.register(eventBus);
        eventBus.addListener(this::registerTabs);
        MinecraftForge.EVENT_BUS.register(this);
        TankModContainers.register(eventBus);
        eventBus.addListener(this::commonSetup);
        TankModBlockEntities.register(eventBus);
        TrajansCoreEntities.register(eventBus);
        ModRecipes.init();
        TrajansCoreNetwork.init();
        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.addListener(this::doClientStuff);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        TrajansCoreNetwork.init();
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        MenuScreens.register(TankModContainers.CRAFTER_CONTAINER.get(), CrafterScreen::new);
        MenuScreens.register(TankModContainers.ENGINE_FABRICATOR_CONTAINER.get(), EngineFabricatorScreen::new);
        MenuScreens.register(TankModContainers.STEEL_MANUFACTURER_CONTAINER.get(), SteelManufacturerScreen::new);
        MenuScreens.register(TankModContainers.PLATING_PRESS_CONTAINER.get(), PlatingPressScreen::new);
        MenuScreens.register(TankModContainers.TURRET_FACTORY_CONTAINER.get(), TurretFactoryScreen::new);
        MenuScreens.register(TankModContainers.SHELL_WORKBENCH_CONTAINER.get(), ShellWorkbenchScreen::new);

        ClientEventHandler.setup();
    }

    public static CreativeModeTab PARTS_TAB;
    public static CreativeModeTab SHELLS_TAB;

    public void registerTabs(CreativeModeTabEvent.Register event) {
        PARTS_TAB = event.registerCreativeModeTab(new ResourceLocation(MOD_ID, "trajanstanks_parts"), builder -> builder
                .icon(() -> new ItemStack(TrajansCoreItems.TANK_CONTROLLER.get()))
                .title(Component.translatable("trajanstanks_parts"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(TrajansCoreItems.CRAFTER_BLOCK_ITEM.get());
                    output.accept(TrajansCoreItems.PLATE_PRESS_BLOCK_ITEM.get());
                    output.accept(TrajansCoreItems.TURRET_FACTORY_BLOCK_ITEM.get());
                    output.accept(TrajansCoreItems.ENGINE_FABRICATOR_ITEM.get());
                    output.accept(TrajansCoreItems.STEEL_MANUFACTURER_ITEM.get());
                    output.accept(TrajansCoreItems.LIGHT_TANK_TURRET.get());
                    output.accept(TrajansCoreItems.LIGHT_ENGINE.get());
                    output.accept(TrajansCoreItems.LIGHT_TANK_PLATING.get());
                    output.accept(TrajansCoreItems.LIGHT_TANK_TRACKS.get());
                    output.accept(TrajansCoreItems.MEDIUM_TANK_TURRET.get());
                    output.accept(TrajansCoreItems.MEDIUM_ENGINE.get());
                    output.accept(TrajansCoreItems.MEDIUM_TANK_PLATING.get());
                    output.accept(TrajansCoreItems.MEDIUM_TANK_TRACMS.get());
                    output.accept(TrajansCoreItems.HEAVY_TANK_TURRET.get());
                    output.accept(TrajansCoreItems.HEAVY_ENGINE.get());
                    output.accept(TrajansCoreItems.HEAVY_TANK_PLATING.get());
                    output.accept(TrajansCoreItems.HEAVY_TANK_TRACKS.get());
                    output.accept(TrajansCoreItems.HAMMER.get());
                    output.accept(TrajansCoreItems.BOLSTER_PLATE.get());
                })
        );
        SHELLS_TAB = event.registerCreativeModeTab(new ResourceLocation(MOD_ID, "trajanscore_shells"), builder -> builder
                .icon(() -> new ItemStack(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get()))
                .title(Component.translatable("trajanscore_shells"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(TrajansCoreItems.STANDARD_SHELL.get());
                    output.accept(TrajansCoreItems.APCR_SHELL.get());
                    output.accept(TrajansCoreItems.ARMOR_PIERCING_SHELL.get());
                    output.accept(TrajansCoreItems.HEAT_SHELL.get());
                    output.accept(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get());
                })
        );
    }
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            BlockEntityRenderers.register(TankModBlockEntities.PLATING_PRESS.get(),context -> new PlatingPressRenderer());
            BlockEntityRenderers.register(TankModBlockEntities.TURRET_FACTORY.get(), context -> new TurretFactoryRenderer());
            BlockEntityRenderers.register(TankModBlockEntities.STEEL_MANUFACTURER.get(), context -> new SteelManufacturerRenderer());
            BlockEntityRenderers.register(TankModBlockEntities.ENGINE_FABRICATOR_BLOCK_ENTITY.get(), context -> new EngineFabricatorRenderer());
            BlockEntityRenderers.register(TankModBlockEntities.SHELL_WORKBENCH.get(), context ->  new ShellWorkbenchRenderer());

            EntityRenderers.register(TrajansCoreEntities.STANDARD_SHELL.get(), StandardShellRenderer::new);
            EntityRenderers.register(TrajansCoreEntities.HIGH_EXPLOSIVE_SHELL.get(), HighExplosiveShellRenderer::new);
            EntityRenderers.register(TrajansCoreEntities.HEAT_SHELL.get(), HeatShellRenderer::new);
            EntityRenderers.register(TrajansCoreEntities.ARMOR_PIERCING_SHELL.get(), ArmorPiercingShellRenderer::new);
            EntityRenderers.register(TrajansCoreEntities.APCR_SHELL.get(), APCRShellRenderer::new);

            EntityRenderers.register(TrajansCoreEntities.EXAMPLE_TANK.get(), ExampleTankRenderer::new);
            EntityRenderers.register(TrajansCoreEntities.EXAMPLE_AT.get(), ExampleATRenderer::new);
        }
    }
}
