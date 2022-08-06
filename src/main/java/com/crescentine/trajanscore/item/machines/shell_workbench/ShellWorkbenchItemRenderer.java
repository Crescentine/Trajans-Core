package com.crescentine.trajanscore.item.machines.shell_workbench;

import com.crescentine.trajanscore.block.shellworkbench.ShellWorkbenchModel;
import com.crescentine.trajanscore.item.machines.steel_manufacturer.SteelManufacturerItem;
import com.crescentine.trajanscore.item.machines.steel_manufacturer.SteelManufacturerItemModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ShellWorkbenchItemRenderer extends GeoItemRenderer<ShellWorkbenchItem> {
    public ShellWorkbenchItemRenderer() {
        super(new ShellWorkbenchItemModel());
    }

}