package com.chaottic.adaptive.villagers.common.structure

import net.minecraft.core.Holder
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider
import net.minecraft.world.level.levelgen.structure.StructureStart
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure
import java.util.*

@Suppress("CAST_NEVER_SUCCEEDS")
class AdaptiveVillagersJigsawStructure(
    structureSettings: StructureSettings,
    startPool: Holder<StructureTemplatePool>,
    startJigsawName: Optional<ResourceLocation>,
    maxDepth: Int,
    startHeight: HeightProvider,
    useExpansionHack: Boolean,
    projectStartToHeightmap: Optional<Heightmap.Types>,
    maxDistanceFromCenter: Int
) : JigsawStructure(structureSettings, startPool, startJigsawName, maxDepth, startHeight, useExpansionHack, projectStartToHeightmap, maxDistanceFromCenter), AdaptiveVillagersStructureSerializable {

    override fun saveCivilizationData(structureStart: StructureStart, tag: CompoundTag) {
        (structureStart as AdaptiveVillagersStructureStartData).let {

            tag["level"] = it.civilizationLevel
        }
    }

    private companion object {

        private operator fun CompoundTag.set(string: String, int: Int) {
            putInt(string, int)
        }
    }
}