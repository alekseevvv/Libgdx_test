package com.art.ava

import com.art.ava.ecs.system.MoveSystem
import com.art.ava.ecs.system.PlayerAnimationSystem
import com.art.ava.ecs.system.PlayerInputSystem
import com.art.ava.ecs.system.RemoveSystem
import com.art.ava.ecs.system.RenderSystem
import com.art.ava.screen.GameScreen
import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g3d.particles.ParticleChannels.TextureRegion
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.app.KtxGame
import ktx.app.KtxScreen

class MyGame : KtxGame<KtxScreen>() {
    val defaultRegion by lazy { TextureRegion(Texture(Gdx.files.internal("graphics/player_mid.png"))) }
    val leftRegion by lazy { TextureRegion(Texture(Gdx.files.internal("graphics/player_left.png"))) }
    val rightRegion by lazy { TextureRegion(Texture(Gdx.files.internal("graphics/player_right.png"))) }

    val gameViewport = FitViewport(800f, 480f)
    val batch: Batch by lazy { SpriteBatch() }
    val engine: Engine by lazy {
        PooledEngine().apply {
            addSystem(PlayerInputSystem(gameViewport))
            addSystem(PlayerAnimationSystem(defaultRegion, leftRegion, rightRegion))
            addSystem(RenderSystem(batch, gameViewport))
            addSystem(RemoveSystem())
            addSystem(MoveSystem())
        }
    }

    override fun create() {
        addScreen(GameScreen(this))
        setScreen<GameScreen>()
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()

        defaultRegion.texture.dispose()
        leftRegion.texture.dispose()
        rightRegion.texture.dispose()
    }
}
