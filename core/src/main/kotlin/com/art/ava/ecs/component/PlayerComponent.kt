package com.art.ava.ecs.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

const val MAX_LIFE = 100f
const val MAX_SHIELD = 100f
class PlayerComponent: Component, Pool.Poolable {
    var life = 100f
    var maxlife = MAX_LIFE
    var shield = 0f
    var maxshield = MAX_SHIELD
    var distance = 0f

    override fun reset() {
         life = 100f
         maxlife = MAX_LIFE
         shield = 0f
         maxshield = MAX_SHIELD
         distance = 0f
    }

    companion object {
        val mapper = mapperFor<PlayerComponent>()
    }
}
