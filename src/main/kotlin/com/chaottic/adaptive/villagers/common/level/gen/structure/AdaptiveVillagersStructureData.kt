package com.chaottic.adaptive.villagers.common.level.gen.structure

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.levelgen.structure.StructureStart

interface AdaptiveVillagersStructureData {

    fun saveCivilizationData(structureStart: StructureStart, tag: CompoundTag)

}