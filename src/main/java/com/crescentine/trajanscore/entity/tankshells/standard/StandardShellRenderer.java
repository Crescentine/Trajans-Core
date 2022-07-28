package com.crescentine.trajanscore.entity.tankshells.standard;

import com.crescentine.trajanscore.item.machines.engine_fabricator.EngineFabricatorItem;
import com.crescentine.trajanscore.item.machines.engine_fabricator.EngineFabricatorItemModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class StandardShellRenderer extends GeoProjectilesRenderer<StandardShellEntity> {
    public StandardShellRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new StandardShellModel());
        this.shadowRadius = 0.2F;
    }
}