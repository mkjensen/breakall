package com.github.mkjensen.breakall.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.mkjensen.breakall.Breakall;

public class DesktopLauncher {

  public static void main(String[] args) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.title = "Breakall";
    config.width = 1000;
    config.height = 1000;
    new LwjglApplication(new Breakall(), config);
  }
}
