package com.crescentine.trajanscore.example_tank;

import com.crescentine.trajanscore.TankModClient;
import com.crescentine.trajanscore.TrajansCoreConfig;
import com.crescentine.trajanscore.basetank.BaseTankEntity;
import com.crescentine.trajanscore.item.TrajansCoreItems;
import com.crescentine.trajanscore.tankshells.apcr.APCRShell;
import com.crescentine.trajanscore.tankshells.armorpiercing.ArmorPiercingShell;
import com.crescentine.trajanscore.tankshells.heat.HeatShell;
import com.crescentine.trajanscore.tankshells.highexplosive.HighExplosiveShell;
import com.crescentine.trajanscore.tankshells.standard.StandardShell;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class ExampleTankEntity extends BaseTankEntity  {
    public ExampleTankEntity(EntityType<?> entityType, Level world) {
        super(entityType, world);
        this.health = 20.0;
        this.speed = 0.25;
        this.shootingCooldown = 20;
        this.armor = 3.0;
        this.healAmount = 5;
        this.maxFuel = 12000;
        this.showFuel = true;
        this.canUseAPCR = true;
        this.canUseArmorPiercing = true;
        this.canUseHeat = true;
        this.canUseHighExplosive = true;
        this.canUseStandard = true;
    }
    @Override
    protected <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        return PlayState.STOP;
    }

}