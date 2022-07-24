package com.chaottic.adaptive.villagers.common.mixin;

import com.chaottic.adaptive.villagers.common.level.gen.structure.StructureTickable;
import com.chaottic.adaptive.villagers.common.level.gen.structure.StructureTickablesAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

@Mixin(ServerLevel.class)
public class ServerLevelMixin implements StructureTickablesAccess {

    private final List<StructureTickable> structureTickables = new ArrayList<>();


    @NotNull
    @Override
    public List<StructureTickable> getToTick() {
        return this.structureTickables;
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void tickStructures(BooleanSupplier booleanSupplier, CallbackInfo ci) {
        for (StructureTickable structureTickable : this.structureTickables) {
            structureTickable.tick((ServerLevel) (Object) this);
        }
    }

    @Inject(method = "unload", at = @At("RETURN"))
    private void removeTickingStructures(LevelChunk levelChunk, CallbackInfo ci) {
        for (StructureStart value : levelChunk.getAllStarts().values()) {
            if (((Object) value) instanceof StructureTickable tickable) {
                this.structureTickables.remove(tickable);
            }
        }
    }
}