package com.crescentine.trajanscore.item.machines.turret_factory;


import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class TurretFactoryItemRenderer extends GeoItemRenderer<TurretFactoryItem> {
    public TurretFactoryItemRenderer() {
        super(new TurretFactoryItemModel());
    }

}