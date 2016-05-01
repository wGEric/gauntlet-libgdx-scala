package com.ericfaerber.gauntlet

import com.badlogic.ashley.core.{Engine, Entity}
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.g2d.{SpriteBatch, TextureRegion}
import com.badlogic.gdx.graphics.{FPSLogger, GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.{Game, Gdx}
import com.ericfaerber.gauntlet.components._
import com.ericfaerber.gauntlet.systems.{InputSystem, MovementSystem, RenderSystem, TiledMapRenderSystem}

class Gauntlet extends Game {

  lazy val entitiesSprite = new Texture("images/entities_32x32.png")
  lazy val batch = new SpriteBatch
  lazy val engine = new Engine
  lazy val fpsLogger = new FPSLogger

  override def create(): Unit = {
    lazy val entitiesRegion = TextureRegion.split(entitiesSprite, BLOCK_SIZE, BLOCK_SIZE)

    val width = Gdx.graphics.getWidth
    val height = Gdx.graphics.getHeight
    val camera = new OrthographicCamera()

    camera.setToOrtho(false, 20, 20 * (height.toFloat / width.toFloat))
    camera.update()

    engine.addSystem(new TiledMapRenderSystem(camera))
    engine.addSystem(new InputSystem)
    engine.addSystem(new MovementSystem)
    engine.addSystem(new RenderSystem(batch, camera))

    val level1 = new TmxMapLoader().load("levels/level01.tmx")
    val map = new Entity()
    map.add(new TiledMapComponent(level1, SCALE))

    engine.addEntity(map)

    val playerObject = level1.getLayers.get("objects").getObjects.get("player")
    val playerProperties = playerObject.getProperties
    val playerEntity = new Entity
    val position = new PositionComponent(playerProperties.get("x", classOf[Float]) * SCALE, playerProperties.get("y", classOf[Float]) * SCALE)

    playerEntity.add(new VelocityComponent(0, 0))
    playerEntity.add(position)
    playerEntity.add(new TextureComponent(Some(entitiesRegion.apply(0).apply(0))))
    playerEntity.add(new InputComponent(up = Keys.UP, down = Keys.DOWN, left = Keys.LEFT, right = Keys.RIGHT))

    engine.addEntity(playerEntity)

    camera.position.x = position.x
    camera.position. y = position.y
  }

  override def render(): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    val deltaTime = Gdx.graphics.getDeltaTime
    engine.update(deltaTime)

    fpsLogger.log()
  }
}
