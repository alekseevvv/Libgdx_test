package com.art.ava.screen

import com.art.ava.MyGame
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Interpolation.SwingOut
import ktx.app.KtxScreen

class SecondScreen(game: MyGame): MyGameScreen(game) {
    override fun show() {
        println("SecondScreen shown")
    }
    override fun render(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            game.setScreen<FirstScreen>()
        }
    }
}
