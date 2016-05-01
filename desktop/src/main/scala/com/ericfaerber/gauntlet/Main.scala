package com.ericfaerber.gauntlet

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

object Main extends App {
    val cfg = new LwjglApplicationConfiguration
    cfg.title = "Gauntlet"
    cfg.height = 480
    cfg.width = 800
    cfg.forceExit = false
    new LwjglApplication(new Gauntlet, cfg)
}
