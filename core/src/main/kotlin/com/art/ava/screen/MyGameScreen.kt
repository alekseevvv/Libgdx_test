package com.art.ava.screen

import com.art.ava.MyGame
import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.g2d.Batch
import ktx.app.KtxScreen

abstract class MyGameScreen(
    val game: MyGame,
    val batch: Batch = game.batch,
    val engine: Engine = game.engine,
    ): KtxScreen
