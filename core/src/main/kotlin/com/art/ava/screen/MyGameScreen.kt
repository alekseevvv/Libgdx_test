package com.art.ava.screen

import com.art.ava.MyGame
import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.app.KtxScreen

abstract class MyGameScreen(
    val game: MyGame,
    val batch: Batch = game.batch,
    val gameViewport: Viewport = game.gameViewport,
    val engine: Engine = game.engine,
) : KtxScreen {

    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
    }

}
