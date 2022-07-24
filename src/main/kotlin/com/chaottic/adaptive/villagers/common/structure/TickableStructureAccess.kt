package com.chaottic.adaptive.villagers.common.structure

interface TickableStructureAccess {

    val toTick: MutableList<TickableStructure>
}