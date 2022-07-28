package com.crescentine.trajanscore.entity.tankshells.highexplosive;

import com.crescentine.trajanscore.entity.tankshells.standard.StandardShellEntity;
import com.crescentine.trajanscore.entity.tankshells.standard.StandardShellModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class HighExplosiveShellRenderer extends GeoProjectilesRenderer<HighExplosiveShellEntity> {
    public HighExplosiveShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HighExplosiveShellModel());
        this.shadowRadius = 0.2F;
    }
}