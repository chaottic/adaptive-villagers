package com.chaottic.adaptive.villagers.common.entity

import net.minecraft.nbt.CompoundTag

class Traits {

    fun load(compoundTag: CompoundTag) {
    }

    fun save(compoundTag: CompoundTag) {
    }

    companion object {
        private const val bitMask = 0xF

        fun create(seed: Int) : Traits {
            val a = seed and bitMask
            val b = (seed shr 3) and bitMask
            val c = (seed shr 6) and bitMask
            val d = (seed shr 8) and bitMask
            val e = (seed shr 11) and bitMask

            return Traits()
        }
    }
}