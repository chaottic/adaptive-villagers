package com.chaottic.adaptive.villagers.common.mixin;

import com.chaottic.adaptive.villagers.common.entity.AdaptiveVillager;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AgeableMob implements AdaptiveVillager {

    private VillagerMixin() {
        //noinspection ConstantConditions
        super(null, null);
    }

    @Inject(method = "brainProvider", at = @At("RETURN"), cancellable = true)
    public void brainProvider(CallbackInfoReturnable<Brain.Provider<?>> cir) {
        cir.setReturnValue(super.brainProvider());
    }

    @Inject(method = "makeBrain", at = @At("RETURN"), cancellable = true)
    public void makeBrain(Dynamic<?> dynamic, CallbackInfoReturnable<Brain<?>> cir) {
        cir.setReturnValue(super.makeBrain(dynamic));
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
