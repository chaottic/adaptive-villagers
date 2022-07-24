package com.chaottic.adaptive.villagers.common.mixin;

import com.chaottic.adaptive.villagers.common.structure.TickableStructure;
import com.chaottic.adaptive.villagers.common.structure.TickableStructureAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ChunkAccess.class)
public abstract class ChunkAccessMixin {

    @Shadow
    @Final
    private Map<Structure, StructureStart> structureStarts;

    @Shadow
    @Final
    protected LevelHeightAccessor levelHeightAccessor;

    @Shadow public abstract ChunkStatus getStatus();

    @Inject(method = "setAllStarts", at = @At("RETURN"))
    private void setAllStarts(Map<Structure, StructureStart> map, CallbackInfo ci) {
        if (getStatus() == ChunkStatus.FULL && levelHeightAccessor instanceof ServerLevel level) {
            structureStarts.forEach((structure, structureStart) -> {
                if (((Object) structureStart) instanceof TickableStructure tickable) {
                    if (tickable.getCanTick()) {
                        ((TickableStructureAccess) level).getToTick().add(tickable);
                    }
                }
            });
        }
    }
}
