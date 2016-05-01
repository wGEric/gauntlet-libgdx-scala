package com.ericfaerber.gauntlet
package systems

import com.badlogic.ashley.core.{ComponentMapper, Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import com.ericfaerber.gauntlet.components.{PositionComponent, TextureComponent}

class RenderSystem(batch: SpriteBatch, camera: OrthographicCamera) extends IteratingSystem(Family.all(classOf[TextureComponent]).get()) {
  val textureMapper = ComponentMapper.getFor(classOf[TextureComponent])
  val positionMapper = ComponentMapper.getFor(classOf[PositionComponent])

  override def update(deltaTime: Float) = {
    camera.update
    batch.setProjectionMatrix(camera.combined)

    batch.begin
    super.update(deltaTime)
    batch.end
  }

  def processEntity(entity: Entity, deltaTime: Float) = {
    val position = positionMapper.get(entity)
    val texture = textureMapper.get(entity).texture

    texture.foreach((t: TextureRegion) => {
      val width = t.getRegionWidth
      val height = t.getRegionHeight
      val originX = width * 0.5f * SCALE
      val originY = height * 0.5f * SCALE

      batch.draw(
        t,
        position.x,
        position.y,
        originX,
        originY,
        width,
        height,
        SCALE,
        SCALE,
        0f
      )
    })
  }
}
