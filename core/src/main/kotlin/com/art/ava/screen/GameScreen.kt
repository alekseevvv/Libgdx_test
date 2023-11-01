package com.art.ava.screen

import com.art.ava.MyGame
import com.art.ava.ecs.component.FacingComponent
import com.art.ava.ecs.component.GraphicComponent
import com.art.ava.ecs.component.MoveComponent
import com.art.ava.ecs.component.PlayerComponent
import com.art.ava.ecs.component.TransformComponent
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import ktx.ashley.entity
import ktx.ashley.with

class GameScreen(game: MyGame) : MyGameScreen(game) {

    override fun show() {
        println("First screen is shown")

        engine.entity {
            with<TransformComponent>() {
                position.set(200f, 400f, 0f)
                size.set(50f, 50f)
            }
            with<GraphicComponent>()
            with<PlayerComponent>()
            with<FacingComponent>()
            with<MoveComponent>()
        }
    }

    override fun render(delta: Float) {

        engine.update(delta)
    }

}
