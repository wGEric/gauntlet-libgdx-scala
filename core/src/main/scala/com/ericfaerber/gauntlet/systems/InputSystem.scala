package com.ericfaerber.gauntlet
package systems

import com.badlogic.ashley.core.{ComponentMapper, Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.ericfaerber.gauntlet.components.{InputComponent, VelocityComponent}

class InputSystem extends IteratingSystem(Family.all(classOf[InputComponent], classOf[VelocityComponent]).get()) {
  val inputMapper = ComponentMapper.getFor(classOf[InputComponent])
  val velocityMapper = ComponentMapper.getFor(classOf[VelocityComponent])

  override def processEntity(entity: Entity, deltaTime: Float) = {
    val input: InputComponent = inputMapper.get(entity)
    val velocity: VelocityComponent = velocityMapper.get(entity)

    if (Gdx.input.isKeyPressed(input.up)) {
      velocity.moveUp
    } else if (Gdx.input.isKeyPressed(input.down)) {
      velocity.moveDown
    }

    if (Gdx.input.isKeyPressed(input.right)) {
      velocity.moveRight
    } else if (Gdx.input.isKeyPressed(input.left)) {
      velocity.moveLeft
    }
  }
}
