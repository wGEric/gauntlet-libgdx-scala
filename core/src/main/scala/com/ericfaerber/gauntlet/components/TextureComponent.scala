package com.ericfaerber.gauntlet.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.TextureRegion

case class TextureComponent(texture: Option[TextureRegion] = None) extends Component
