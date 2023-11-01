package com.art.ava.ecs.system

import com.art.ava.ecs.component.FacingComponent
import com.art.ava.ecs.component.FacingDirection
import com.art.ava.ecs.component.MoveComponent
import com.art.ava.ecs.component.PlayerComponent
import com.art.ava.ecs.component.RemoveComponent
import com.art.ava.ecs.component.TransformComponent
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import ktx.ashley.allOf
import ktx.ashley.get
import kotlin.math.max
import kotlin.math.min

private const val UPDATE_RATE = 1 / 25f
private const val HOR_ACCELERATION = 16.5f
private const val VER_ACCELERATION = 2.25f
private const val MAX_VER_NEG_PLAYER = 0.75f
private const val MAX_HOR_SPEED = 5.5f
private const val MAX_VER_SPEED = 5.5f

class MoveSystem : IteratingSystem(
    allOf
        (TransformComponent::class, MoveComponent::class).exclude(RemoveComponent::class.java).get()
) {

    private var accumulator = 0f
    override fun update(deltaTime: Float) {
        accumulator += deltaTime
        while (accumulator >= UPDATE_RATE) {
            accumulator -= UPDATE_RATE
            super.update(UPDATE_RATE)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transform = entity[TransformComponent.mapper]
        require(transform != null) {}
        val move = entity[MoveComponent.mapper]
        require(move != null)
        val player = entity[PlayerComponent.mapper]
        if (player != null) {
            //player movement
            entity[FacingComponent.mapper]?.let { facing ->
                movePlayer(transform, move, player, facing, deltaTime)
            }
        } else {
            //other movement like powerups, enemy
            moveEntity(transform, move, deltaTime)
        }
    }

    private fun movePlayer(
        transform: TransformComponent,
        move: MoveComponent,
        player: PlayerComponent,
        facing: FacingComponent,
        deltaTime: Float
    ){
        // upadate horizontal speed
        move.speed.x = when(facing.direction){
            FacingDirection.LEFT -> min(0f, move.speed.x - HOR_ACCELERATION * deltaTime)
            FacingDirection.RIGHT -> max(0f, move.speed.x + HOR_ACCELERATION * deltaTime)
            else -> 0f
        }
        move.speed.x = MathUtils.clamp(move.speed.x, -MAX_HOR_SPEED, MAX_HOR_SPEED)

        //update vertical speed
        move.speed.y = MathUtils.clamp(move.speed.y - VER_ACCELERATION * deltaTime,
        -MAX_VER_NEG_PLAYER, MAX_VER_NEG_PLAYER)

        moveEntity(transform, move, deltaTime)
    }

    private fun moveEntity(transform: TransformComponent, move: MoveComponent, deltaTime: Float) {
        transform.position.x = MathUtils.clamp(
            transform.position.x + move.speed.x * deltaTime,
            0f,
            16 - transform.size.x
        )
        transform.position.y = MathUtils.clamp(
            transform.position.y + move.speed.y * deltaTime,
            1f,
            10f - transform.size.y
        )
    }
}
