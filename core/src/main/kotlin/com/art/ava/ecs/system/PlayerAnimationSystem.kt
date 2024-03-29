package com.art.ava.ecs.system

import com.art.ava.ecs.component.FacingComponent
import com.art.ava.ecs.component.FacingDirection
import com.art.ava.ecs.component.GraphicComponent
import com.art.ava.ecs.component.PlayerComponent
import com.art.ava.ecs.component.TransformComponent
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ktx.ashley.allOf
import ktx.ashley.get

class PlayerAnimationSystem(
    private val defaultRegion: TextureRegion,
    private val leftRegion: TextureRegion,
    private val rightRegion: TextureRegion
): IteratingSystem(allOf(FacingComponent::class, PlayerComponent::class, GraphicComponent::class).get()),
    EntityListener {
    var lastDirection = FacingDirection.DEFAULT

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.addEntityListener(family, this)
    }

    override fun removedFromEngine(engine: Engine) {
        super.removedFromEngine(engine)
        engine.removeEntityListener(this)
    }

    override fun entityAdded(entity: Entity) {
        entity[GraphicComponent.mapper]?.setSpriteRegion(defaultRegion)
    }

    override fun entityRemoved(entity: Entity?) = Unit


    override fun processEntity(entity: Entity, deltaTime: Float) {
        val facing = entity[FacingComponent.mapper]
        require(facing != null){"Entity ..."}
        val graphic = entity[GraphicComponent.mapper]
        require(graphic != null){"Entity .. transform"}

        if (facing.direction == lastDirection && graphic.sprite.texture != null){
            //texture set -> do nothing
        }
        lastDirection = facing.direction
        val region = when(facing.direction){
            FacingDirection.RIGHT -> rightRegion
            FacingDirection.LEFT -> leftRegion
            else -> defaultRegion
        }
        graphic.setSpriteRegion(region)
    }
}
