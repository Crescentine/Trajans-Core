package com.crescentine.trajanscore.mixin;


import com.crescentine.trajanscore.basetank.BaseATEntity;
import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.crescentine.trajanscore.basetrackedvehicle.BaseTrackedVehicle;
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

    //uh
    @Shadow
    Input input;

    @Shadow @Final
    protected Minecraft minecraft;



    @Inject(method = "rideTick", at = @At(value = "TAIL"))

    public void rideTick(CallbackInfo ci) {
        LocalPlayer self = (LocalPlayer)(Object)this;
        if (self.getVehicle() instanceof BaseTankEntity tank) {
            tank.sendClientInputInfo(this.input.left, this.input.right, this.input.up, this.input.down);
        }
        if (self.getVehicle() instanceof BaseATEntity at) {
            at.setInput(this.input.up, this.input.down);

        }

        if (self.getVehicle() instanceof BaseTrackedVehicle t) {
            t.setInput(this.input.left, this.input.right, this.input.up, this.input.down);

        }
    }

}
