package com.crescentine.trajanscore.mixin;

import com.crescentine.trajanscore.TankUtilMain;
import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.text.html.parser.Entity;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    @Inject(method = "renderFlame(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private void renderFlame(PoseStack pMatrixStack, MultiBufferSource pBuffer, net.minecraft.world.entity.Entity pEntity, CallbackInfo ci) {
        if (pEntity instanceof Player player && TankUtilMain.playerIsInTank(player) && ((BaseTankEntity) player.getControlledVehicle()).isVisiblePlayer)
            ci.cancel();
    }

    @Inject(method = "renderHitbox(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lnet/minecraft/world/entity/Entity;F)V", at = @At("HEAD"), cancellable = true)
    private static void renderHitbox(PoseStack pMatrixStack, VertexConsumer pBuffer, net.minecraft.world.entity.Entity pEntity, float pPartialTicks, CallbackInfo ci) {
        if (pEntity instanceof Player player && TankUtilMain.playerIsInTank(player) && ((BaseTankEntity) player.getControlledVehicle()).isVisiblePlayer) ci.cancel();
    }

    @Inject(method = "renderShadow(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/Entity;FFLnet/minecraft/world/level/LevelReader;F)V", at = @At("HEAD"), cancellable = true)
    private static void renderShadow(PoseStack pMatrixStack, MultiBufferSource pBuffer, net.minecraft.world.entity.Entity pEntity, float pWeight, float pPartialTicks, LevelReader pLevel, float pSize, CallbackInfo ci) {
        if (pEntity instanceof Player player && TankUtilMain.playerIsInTank(player) && ((BaseTankEntity) player.getControlledVehicle()).isVisiblePlayer) ci.cancel();
    }
}
