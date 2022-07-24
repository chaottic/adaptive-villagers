package com.chaottic.adaptive.villagers.common.mixin;

import com.chaottic.adaptive.villagers.common.level.gen.structure.AdaptiveJigsawStructure;
import com.chaottic.adaptive.villagers.common.level.gen.structure.AdaptiveVillagersStructureData;
import com.chaottic.adaptive.villagers.common.level.gen.structure.AdaptiveVillagersStructureStartData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
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
    private int civilizationLevel;

    private boolean isTicking;
    private boolean isAdaptiveVillagersStructureStart;


    @Inject(method = "<init>", at = @At("RETURN"))
    private void StructureStart(Structure structure, ChunkPos chunkPos, int i, PiecesContainer piecesContainer, CallbackInfo ci) {
        this.isTicking = structure instanceof AdaptiveJigsawStructure;
        this.isAdaptiveVillagersStructureStart = structure instanceof AdaptiveJigsawStructure;
    }

    @Override
    public int getCivilizationLevel() {
        return this.civilizationLevel;
    }

    @Override
    public void setCivilizationLevel(int value) {
        this.civilizationLevel = value;
    }

    @Override
    public boolean isAdaptiveVillagersStructureStart() {
        return this.isAdaptiveVillagersStructureStart;
    }

    @Override
    public void setAdaptiveVillagersStructureStart(boolean value) {

    }

    @Override
    public boolean isTicking() {
        return this.isTicking;
    }

    @Override
    public void setTicking(boolean value) {
    }

    @Inject(method = "createTag", at = @At("RETURN"))
    private void addStructureData(StructurePieceSerializationContext structurePieceSerializationContext, ChunkPos chunkPos, CallbackInfoReturnable<CompoundTag> cir) {
        if (structure instanceof AdaptiveVillagersStructureData adaptiveVillagersStructureData) {
            adaptiveVillagersStructureData.saveCivilizationData((StructureStart) (Object) this, cir.getReturnValue());
        }
    }

    @Override
    public void tick(ServerLevel serverLevel) {

    }
}
