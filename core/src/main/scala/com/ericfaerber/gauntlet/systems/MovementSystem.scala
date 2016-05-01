package com.ericfaerber.gauntlet.systems

import com.badlogic.ashley.core._
import com.badlogic.ashley.systems.IteratingSystem
import com.ericfaerber.gauntlet.components.{PositionComponent, VelocityComponent}

class MovementSystem extends IteratingSystem(Family.all(classOf[PositionComponent], classOf[VelocityComponent]).get()) {
  val positionMapper = ComponentMapper.getFor(classOf[PositionComponent])
  val velocityMapper = ComponentMapper.getFor(classOf[VelocityComponent])

  def processEntity(entity: Entity, deltaTime: Float) = {
    val position: PositionComponent = positionMapper.get(entity)
    val velocity: VelocityComponent = velocityMapper.get(entity)

    position.moveBy(velocity.x, velocity.y)
    velocity.stop()
  }
}
