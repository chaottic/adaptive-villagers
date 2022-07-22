package com.chaottic.adaptive.villagers.common

import com.chaottic.adaptive.villagers.common.block.AdaptiveVillagersBlocks
import com.chaottic.adaptive.villagers.common.entity.AdaptiveVillagersEntities
import com.chaottic.adaptive.villagers.common.item.AdaptiveVillagersItems
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.slf4j.LoggerFactory

class AdaptiveVillagers : ModInitializer {

    override fun onInitialize(mod: ModContainer?) {

        AdaptiveVillagersBlocks.register()
        AdaptiveVillagersItems.register()
        AdaptiveVillagersEntities.register()
    }

    companion object {
        private const val modId = "adaptive_villagers"

        private val logger = LoggerFactory.getLogger("Adaptive Villagers")
    }
}