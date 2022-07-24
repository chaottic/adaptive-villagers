package com.chaottic.adaptive.villagers.common.level.gen.structure

import net.minecraft.server.level.ServerLevel

interface StructureTickable {

    var isTicking: Boolean

    fun tick(serverLevel: ServerLevel)
}