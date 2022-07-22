package com.chaottic.adaptive.villagers.common

import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.slf4j.LoggerFactory

class AdaptiveVillagers : ModInitializer {

    override fun onInitialize(mod: ModContainer?) {
    }

    companion object {
        private const val modId = "adaptive_villagers"

        private val logger = LoggerFactory.getLogger("Adaptive Villagers")
    }
}