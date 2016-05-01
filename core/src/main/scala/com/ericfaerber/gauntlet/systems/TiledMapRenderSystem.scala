package com.ericfaerber.gauntlet.systems

import com.badlogic.ashley.core.{ComponentMapper, Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.ericfaerber.gauntlet.components.TiledMapComponent

class TiledMapRenderSystem(camera: OrthographicCamera) extends IteratingSystem(Family.all(classOf[TiledMapComponent]).get()) {
  val tiledMapMapper = ComponentMapper.getFor(classOf[TiledMapComponent])

  def processEntity(entity: Entity, deltaTime: Float) = {
    val tiledMap = tiledMapMapper.get(entity)
    tiledMap.renderer.setView(camera)
    tiledMap.renderer.render()
  }
}
