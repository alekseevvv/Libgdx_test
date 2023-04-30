package com.art.ava

import com.badlogic.gdx.Game

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class MyGame : Game() {
    override fun create() {
        setScreen(FirstScreen())
    }
}
