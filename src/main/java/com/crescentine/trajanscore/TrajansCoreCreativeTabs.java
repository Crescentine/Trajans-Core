package com.crescentine.trajanscore;

import com.crescentine.trajanscore.item.TrajansCoreItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TrajansCoreCreativeTabs {
    public static DeferredRegister<CreativeModeTab> CREATIVE_TABS
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TrajansCoreMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PARTS_TAB = CREATIVE_TABS.register("trajanscore_parts", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .title(Component.translatable("itemgroup.trajanstanks_parts"))
            .icon(() -> TrajansCoreItems.TANK_CONTROLLER.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
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
            }).build());

    public static final RegistryObject<CreativeModeTab> SHELLS_TAB = CREATIVE_TABS.register("trajanscore_shells", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> new ItemStack(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get()))
            .title(Component.translatable("itemgroup.trajanscore_shells"))
            .displayItems((parameters, output) -> {
                output.accept(TrajansCoreItems.STANDARD_SHELL.get());
                output.accept(TrajansCoreItems.APCR_SHELL.get());
                output.accept(TrajansCoreItems.ARMOR_PIERCING_SHELL.get());
                output.accept(TrajansCoreItems.HEAT_SHELL.get());
                output.accept(TrajansCoreItems.HIGH_EXPLOSIVE_SHELL.get());
                output.accept(TrajansCoreItems.LOW_CALIBER_SHELL.get());

            }).build());
    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}
