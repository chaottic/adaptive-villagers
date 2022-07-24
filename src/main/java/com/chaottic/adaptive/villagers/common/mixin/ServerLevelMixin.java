package com.chaottic.adaptive.villagers.common.mixin;

import com.chaottic.adaptive.villagers.common.structure.TickableStructure;
import com.chaottic.adaptive.villagers.common.structure.TickableStructureAccess;
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
public class ServerLevelMixin implements TickableStructureAccess {

    private final List<TickableStructure> toTick = new ArrayList<>();

    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(BooleanSupplier booleanSupplier, CallbackInfo ci) {
        toTick.forEach(tickable -> tickable.tick((ServerLevel) (Object) this));
    }

    @Inject(method = "unload", at = @At("RETURN"))
    private void unload(LevelChunk levelChunk, CallbackInfo ci) {
        for (StructureStart value : levelChunk.getAllStarts().values()) {
            if (((Object) value) instanceof TickableStructure tickable) {
                toTick.remove(tickable);
            }
        }
    }

    @NotNull
    @Override
    public List<TickableStructure> getToTick() {
        return toTick;
    }
}