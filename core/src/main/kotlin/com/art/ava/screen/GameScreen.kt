package com.art.ava.screen

import com.art.ava.MyGame
import com.art.ava.ecs.component.GraphicComponent
import com.art.ava.ecs.component.TransformComponent
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import ktx.ashley.entity
import ktx.ashley.with

class GameScreen(game: MyGame) : MyGameScreen(game) {
    private val playerTexture = Texture(Gdx.files.internal("graphics/alpaca.png"))

    override fun show() {
        repeat(10){
            engine.entity{
                with<TransformComponent>() {
                    position.set(MathUtils.random(0f,800f), MathUtils.random(0f,480f), 0f)
                    size.set(50f,50f)
                }
                with<GraphicComponent>() {
                    sprite.run {
                        setRegion(playerTexture)
                        setSize(texture.width.toFloat(), texture.height.toFloat())
                        setOriginCenter()
                    }
                }
            }
        }
        println("First screen is shown")
    }

    override fun render(delta: Float) {

        engine.update(delta)
    }

    override fun dispose() {
        playerTexture.dispose()
    }

}
