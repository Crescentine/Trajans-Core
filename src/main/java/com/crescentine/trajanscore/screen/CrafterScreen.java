package com.crescentine.trajanscore.screen;

import com.crescentine.trajanscore.TrajansCoreMod;
import com.crescentine.trajanscore.container.CrafterContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CrafterScreen extends AbstractContainerScreen<CrafterContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TrajansCoreMod.MOD_ID, "textures/gui/crafter.png");

    public CrafterScreen(CrafterContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 175;
        this.imageHeight = 165;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        guiGraphics.drawString(font, this.title, this.leftPos + 20, this.topPos + 5, 0x404040, false);
        guiGraphics.drawString(font, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 75, 0x404040, false);
    }
    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {
    }
}
