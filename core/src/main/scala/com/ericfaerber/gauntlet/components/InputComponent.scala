package com.ericfaerber.gauntlet.components

import com.badlogic.ashley.core.Component

case class InputComponent(up: Integer, left: Integer, down: Integer, right: Integer) extends Component
