package com.art.ava.ecs.system

import com.art.ava.ecs.component.FacingComponent
import com.art.ava.ecs.component.FacingDirection
import com.art.ava.ecs.component.PlayerComponent
import com.art.ava.ecs.component.TransformComponent
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.ashley.allOf
import ktx.ashley.get

private const val TOUCH_DISTANCE = 0.8f
//Настроить нормально пропорции
class PlayerInputSystem(
    private val gameViewport: Viewport

) : IteratingSystem(
    allOf(
        PlayerComponent::class,
        FacingComponent::class,
        TransformComponent::class
    ).get()
) {
    private val temVec = Vector2()

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val facing = entity?.get(FacingComponent.mapper)
        require(facing != null){"Entity ..."}
        val transform = entity[TransformComponent.mapper]
        require(transform != null){"Entity .. transform"}

        temVec.x = Gdx.input.x.toFloat()
        gameViewport.unproject(temVec)
        val diffX = temVec.x - transform.position.x - transform.size.x * 0.5f
        println("facing direciton = ${facing.direction.name}")
        facing.direction = when{
            diffX < -TOUCH_DISTANCE -> FacingDirection.LEFT
            diffX > TOUCH_DISTANCE  -> FacingDirection.RIGHT
            else -> FacingDirection.DEFAULT
        }
    }
}
