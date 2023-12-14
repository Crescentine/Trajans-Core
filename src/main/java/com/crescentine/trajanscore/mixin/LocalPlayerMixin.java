package com.crescentine.trajanscore.mixin;


import com.crescentine.trajanscore.basetank.BaseTankEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.animal.TropicalFish;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {


    @Shadow
    Input input;

    @Shadow @Final
    protected Minecraft minecraft;



    @Inject(method = "rideTick", at = @At(value = "TAIL"))

    public void rideTick(CallbackInfo ci) {
        LocalPlayer self = (LocalPlayer)(Object)this;
        if (self.getVehicle() instanceof BaseTankEntity tank) {
            tank.setInput(this.input.left, this.input.right, this.input.up, this.input.down);

        }

    }

}
