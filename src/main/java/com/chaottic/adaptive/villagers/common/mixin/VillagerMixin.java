package com.chaottic.adaptive.villagers.common.mixin;

import com.chaottic.adaptive.villagers.common.entity.AdaptiveVillager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AgeableMob implements AdaptiveVillager {

    private VillagerMixin() {
        //noinspection ConstantConditions
        super(null, null);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci) {
        ci.cancel();

        super.tick();
    }

    @Inject(method = "customServerAiStep", at = @At("HEAD"), cancellable = true)
    public void customServerAiStep(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"), cancellable = true)
    public void addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        ci.cancel();

        super.addAdditionalSaveData(compoundTag);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"), cancellable = true)
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        ci.cancel();

        super.readAdditionalSaveData(compoundTag);
    }
}
