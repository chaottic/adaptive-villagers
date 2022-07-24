package com.chaottic.adaptive.villagers.common.structure

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.levelgen.structure.StructureStart

interface AdaptiveVillagersStructureSerializable {

    fun saveCivilizationData(structureStart: StructureStart, tag: CompoundTag)
}