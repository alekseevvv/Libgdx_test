package com.art.ava.screen

import com.art.ava.MyGame
import com.art.ava.ecs.component.GraphicComponent
import com.art.ava.ecs.component.TransformComponent
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.ashley.entity
import ktx.ashley.get
import ktx.ashley.with
import ktx.graphics.use

class GameScreen(game: MyGame) : MyGameScreen(game) {
    private val viewPort = FitViewport(800f, 480f)
    private val playerTexture = Texture(Gdx.files.internal("graphics/alpaca.png"))

    private val player = game.engine.entity {
        with<TransformComponent>() {
            position.set(1f, 1f, 0f)
            size.set(100f, 100f)
            rotationDeg = 1f
        }
        with<GraphicComponent>() {
            sprite.run {
                setRegion(playerTexture)
                setSize(texture.width.toFloat(), texture.height.toFloat())
                setOriginCenter()
            }
        }
    }

    override fun show() {
        println("First screen is shown")
    }

    override fun resize(width: Int, height: Int) {
        viewPort.update(width, height, true)
    }

    override fun render(delta: Float) {
        engine.update(delta)

        viewPort.apply()
        batch.use(viewPort.camera.combined) { batch ->
            player[GraphicComponent.mapper]?.let { graphic ->
                player[TransformComponent.mapper]?.let { transform ->
                    graphic.sprite.run {
                        rotation = transform.rotationDeg
                        setBounds(transform.position.x, transform.position.y, transform.size.x, transform.size.y)
                        draw(batch)
                    }
                }
            }
        }
    }

    override fun dispose() {
        playerTexture.dispose()
    }

}
