package com.ericfaerber.gauntlet.components

import com.badlogic.ashley.core.Component

case class PositionComponent(var x: Float, var y: Float) extends Component {
  def moveTo(x: Float, y: Float) = {
    this.x = x
    this.y = y
  }

  def moveBy(x: Float, y: Float) = {
    this.x += x
    this.y += y
  }
}
