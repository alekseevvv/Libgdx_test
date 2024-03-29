package com.art.ava.ecs.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class GraphicComponent: Component, Pool.Poolable {
    val sprite = Sprite()


    override fun reset() {
        sprite.texture = null
        sprite.setColor(1f,1f,1f,1f)
    }

    fun setSpriteRegion(region: TextureRegion){
        sprite.run {
            setRegion(region)
            setSize(texture.width.toFloat(), texture.height.toFloat())
            setOriginCenter()
        }

    }
    companion object{
        val mapper: ComponentMapper<GraphicComponent> = mapperFor<GraphicComponent>()
    }

}
