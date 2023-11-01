package com.art.ava.ecs.system

import com.art.ava.ecs.component.RemoveComponent
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import ktx.ashley.get

class RemoveSystem: IteratingSystem(allOf(RemoveComponent::class).get()) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val remove: RemoveComponent? = entity?.get(RemoveComponent.mapper)
        require(remove != null){

        }

        remove.delay -= deltaTime
        if (remove.delay <= 0f){
            engine.removeEntity(entity)
        }
    }

}
