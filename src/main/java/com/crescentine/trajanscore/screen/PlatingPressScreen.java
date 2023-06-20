package com.crescentine.trajanscore.screen;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.container.PlatingPressContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.logging.Level;

public class PlatingPressScreen extends AbstractContainerScreen<PlatingPressContainer> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/plating_press.png");

    public PlatingPressScreen(PlatingPressContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        //old   this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        if (menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 65, y + 29, 176, 0, menu.getScaledProgress(), 40);
        }
        //old      blit(pPoseStack, x + 97, y + 35, 176, 0, menu.getScaledProgress(), 14);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}