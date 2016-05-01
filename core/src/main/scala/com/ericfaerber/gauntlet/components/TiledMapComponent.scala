package com.ericfaerber.gauntlet
package components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.{TiledMap, TiledMapRenderer}
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer

case class TiledMapComponent(map: TiledMap, scale: Float = SCALE) extends Component {
  val renderer: TiledMapRenderer = new OrthogonalTiledMapRenderer(map, scale)
}
