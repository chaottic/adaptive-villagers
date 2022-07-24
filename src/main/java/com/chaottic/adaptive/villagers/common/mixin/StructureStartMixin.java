package com.chaottic.adaptive.villagers.common.mixin;

import com.chaottic.adaptive.villagers.common.structure.AdaptiveVillagersJigsawStructure;
import com.chaottic.adaptive.villagers.common.structure.AdaptiveVillagersStructureSerializable;
import com.chaottic.adaptive.villagers.common.structure.AdaptiveVillagersStructureStartData;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureStart.class)
public class StructureStartMixin implements AdaptiveVillagersStructureStartData {

    @Shadow
    @Final
    private Structure structure;

    @Getter
    @Setter
    private int civilizationLevel;

    private boolean canTick;

    @Setter
    private boolean fromAdaptiveVillagers;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(Structure structure, ChunkPos chunkPos, int i, PiecesContainer piecesContainer, CallbackInfo ci) {
        fromAdaptiveVillagers = structure instanceof AdaptiveVillagersJigsawStructure;

        canTick = fromAdaptiveVillagers;
    }

    @Inject(method = "createTag", at = @At("RETURN"))
    private void createTag(StructurePieceSerializationContext structurePieceSerializationContext, ChunkPos chunkPos, CallbackInfoReturnable<CompoundTag> cir) {
        if (structure instanceof AdaptiveVillagersStructureSerializable serializable) {
            serializable.saveCivilizationData((StructureStart) (Object) this, cir.getReturnValue());
        }
    }

    @Override
    public void tick(@NotNull ServerLevel level) {
    }

    @Override
    public boolean getCanTick() {
        return canTick;
    }

    @Override
    public void setCanTick(boolean value) {
        canTick = value;
    }

    @Override
    public boolean getFromAdaptiveVillagers() {
        return fromAdaptiveVillagers;
    }
}
