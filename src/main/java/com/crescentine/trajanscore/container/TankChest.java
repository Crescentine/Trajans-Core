package com.crescentine.trajanscore.container;

import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class TankChest extends AbstractContainerMenu {

    private final Container tankContainer;
    private final BaseTankEntity baseTank;



    public TankChest(int pContainerId, Inventory pPlayerInventory, Container pContainer, final BaseTankEntity Tank) {
        super(MenuType.GENERIC_9x1, pContainerId);
        this.tankContainer = pContainer;
        this.baseTank = Tank;
        pContainer.startOpen(pPlayerInventory.player);
        this.addSlot(new Slot(pContainer, 0, 8, 18));
    }



    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }
}
