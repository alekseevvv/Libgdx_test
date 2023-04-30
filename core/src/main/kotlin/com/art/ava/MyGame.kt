package com.art.ava

import com.art.ava.screen.FirstScreen
import com.art.ava.screen.MyGameScreen
import com.art.ava.screen.SecondScreen
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.app.KtxScreen

class MyGame : KtxGame<KtxScreen>() {
    val batch: Batch by lazy { SpriteBatch() }
    val engine: Engine by lazy { PooledEngine() }

    override fun create() {
        addScreen(FirstScreen(this))
        setScreen<FirstScreen>()
    }

    override fun dispose() {
        batch.dispose()
    }
}
