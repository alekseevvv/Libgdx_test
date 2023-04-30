package com.art.ava.screen

import com.art.ava.MyGame
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.graphics.use

class FirstScreen(game: MyGame): MyGameScreen(game) {
    private val viewPort = FitViewport(210f, 400f)
    private val texture = Texture(Gdx.files.internal("graphics/pirate_ship.png"))
    private val sprite = Sprite(texture).apply {
        setSize(21f,40f)
    }
    override fun show() {
        sprite.setPosition(1f,1f)
        println("First screen is shown")
    }

    override fun resize(width: Int, height: Int) {
        viewPort.update(width,height,true)
    }

    override fun render(delta: Float) {
        engine.update(delta)

        viewPort.apply()
        batch.use(viewPort.camera.combined) {
            sprite.draw(it)
        }
    }

    override fun dispose() {
        texture.dispose()
    }

}
