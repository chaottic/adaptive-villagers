package com.chaottic.adaptive.villagers.common.structure

import net.minecraft.server.level.ServerLevel

interface TickableStructure {

    var canTick: Boolean

    fun tick(serverLevel: ServerLevel)
}