package com.art.ava.ecs.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class RemoveComponent : Component, Pool.Poolable {
    var delay = 0f

    override fun reset() {
        delay = 0f
    }

    companion object {
        val mapper: ComponentMapper<RemoveComponent> = mapperFor<RemoveComponent>()
    }
}
