package com.crescentine.trajanscore;

import net.minecraftforge.common.ForgeConfigSpec;

public class TrajansCoreConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> fuelSystemEnabled;
    public static final ForgeConfigSpec.ConfigValue<Double> coalFuelAmount;
    public static final ForgeConfigSpec.ConfigValue<Double> lavaFuelAmount;
    public static final ForgeConfigSpec.ConfigValue<Boolean> tanksImmuneToFire;
    public static final ForgeConfigSpec.ConfigValue<Boolean> arrowsDamageTanks;

    public static final ForgeConfigSpec.ConfigValue<Boolean> meleeDamageTanks;



    public static final ForgeConfigSpec.ConfigValue<Double> standardShellDamage;
    public static final ForgeConfigSpec.ConfigValue<Integer> standardShellExplosionRadius;

    public static final ForgeConfigSpec.ConfigValue<Double> armorPiercingShellDamage;
    public static final ForgeConfigSpec.ConfigValue<Double> armorPiercingShellDamageToArmoredVehicles;
    public static final ForgeConfigSpec.ConfigValue<Integer> armorPiercingExplosionRadius;

    public static final ForgeConfigSpec.ConfigValue<Double> highExplosiveShellDamage;
    public static final ForgeConfigSpec.ConfigValue<Integer> highExplosiveShellExplosionRadius;

    public static final ForgeConfigSpec.ConfigValue<Double> heatShellDamage;
    public static final ForgeConfigSpec.ConfigValue<Integer> heatShellExplosionRadius;

    public static final ForgeConfigSpec.ConfigValue<Double> APCRShellDamage;
    public static final ForgeConfigSpec.ConfigValue<Integer> APCRShellExplosionRadius;
    public static final ForgeConfigSpec.ConfigValue<Double> LowCaliberShellDamage;
    public static final ForgeConfigSpec.ConfigValue<Integer> LowCaliberShellExplosionRadius;


    static {
        BUILDER.push("Trajan's Core Config");
        BUILDER.comment("Config File for Trajan's Core Mod");
        BUILDER.pop();

        BUILDER.push("General");
        fuelSystemEnabled = BUILDER.define("Set to false to disable tanks requiring fuel", true);
        tanksImmuneToFire = BUILDER.define("Set to true to make tanks affected by fire", false);
        arrowsDamageTanks = BUILDER.define("Set to true to make tanks affected by arrows", false);
        meleeDamageTanks = BUILDER.define("Set to true to make tanks affected by melee", false);
        coalFuelAmount = BUILDER.define("How Much Fuel Coal and Charcoal Gives (seconds)", 6.0);
        lavaFuelAmount = BUILDER.define("How Much Fuel Lava Gives (seconds)", 60.0);
        BUILDER.pop();

        BUILDER.push("Standard Shell Stats");
        standardShellDamage = BUILDER.define("Standard Shell Damage", 60.0);
        standardShellExplosionRadius = BUILDER.define("Standard Shell Explosion Radius", 3);
        BUILDER.pop();

        BUILDER.push("Armor Piercing Shell Stats");
        armorPiercingShellDamage = BUILDER.define("Armor Piercing Shell Damage (to players)", 60.0);
        armorPiercingExplosionRadius = BUILDER.define("Armor Piercing Shell Explosion Radius", 3);
        armorPiercingShellDamageToArmoredVehicles = BUILDER.define("Armor Piercing Shell Damage (to armored vehicles)", 80.0);
        BUILDER.pop();

        BUILDER.push("High Explosive Shell Stats");
        highExplosiveShellDamage = BUILDER.define("High Explosive Shell Damage", 60.0);
        highExplosiveShellExplosionRadius = BUILDER.define("High Explosive Shell Explosion Radius", 4);
        BUILDER.pop();

        BUILDER.push("HEAT Shell Stats");
        heatShellDamage = BUILDER.define("HEAT Shell Damage", 80.0);
        heatShellExplosionRadius = BUILDER.define("HEAT Shell Explosion Radius", 2);
        BUILDER.pop();

        BUILDER.push("APCR Shell Stats");
        APCRShellDamage = BUILDER.define("APCR Shell Damage", 90.0);
        APCRShellExplosionRadius = BUILDER.define("ACPR Shell Explosion Radius", 0);
        BUILDER.pop();

        BUILDER.push("Low Caliber Shell Stats");
        LowCaliberShellDamage = BUILDER.define("Low Caliber Shell Damage", 20.0);
        LowCaliberShellExplosionRadius = BUILDER.define("Low Caliber Shell Explosion Radius", 0);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
