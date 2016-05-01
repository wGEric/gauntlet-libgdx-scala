package com.ericfaerber.gauntlet
package components

import com.badlogic.ashley.core.Component

case class VelocityComponent(var x: Float, var y: Float, speed: Float = 2) extends Component {
  val calculatedSpeed = speed * SCALE

  def stop(): Unit = {
    y = 0
    x = 0
  }

  def moveUp: Float = {
    y = calculatedSpeed
    y
  }

  def moveDown: Float = {
    y = -1 * calculatedSpeed
    y
  }

  def moveLeft: Float = {
    x = -1 * calculatedSpeed
    x
  }

  def moveRight: Float = {
    x = calculatedSpeed
    x
  }
}
