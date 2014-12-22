/**
 * Copyright 2014 Martin Kamp Jensen
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.mkjensen.breakall;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.mkjensen.breakall.Logger.Level;
import com.github.mkjensen.breakall.actor.Ball;
import com.github.mkjensen.breakall.actor.Bricks;
import com.github.mkjensen.breakall.actor.Paddle;
import com.github.mkjensen.breakall.actor.Walls;

public class Breakall extends ApplicationAdapter {

  private static final Logger LOG = Logger.getLogger(Breakall.class);
  private World world;
  private Stage stage;
  private DebugRenderer debugRenderer;
  private Walls walls;
  private Bricks bricks;
  private Paddle paddle;

  @Override
  public void create() {
    Logger.setLevel(Level.DEBUG);
    createRenderer();
    createWalls();
    createBricks();
    createPaddle();
  }

  private void createRenderer() {
    world = new World();
    FitViewport viewport = new FitViewport(world.getSize(), world.getSize());
    stage = new Stage(viewport);
    debugRenderer = new DebugRenderer(world, stage.getCamera());
    Gdx.input.setInputProcessor(stage);
  }

  private void createWalls() {
    walls = new Walls(world);
    stage.addActor(walls);
  }

  private void createBricks() {
    bricks = new Bricks(world);
    stage.addActor(bricks);
  }

  private void createPaddle() {
    paddle = new Paddle(world);
    stage.addActor(paddle);

    stage.addListener(new InputListener() {

      @Override
      public boolean keyUp(InputEvent event, int keycode) {
        switch (keycode) {
          case Keys.F11:
            debugRenderer.setEnabled(!debugRenderer.isEnabled());
            return true;
          case Keys.F12:
            stage.getRoot().setVisible(!stage.getRoot().isVisible());
            return true;
          default:
            return false;
        }
      }

      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return button == 0;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Ball ball = new Ball(world);
        ball.setPosition(paddle.getX(), paddle.getY() + paddle.getHeight());
        stage.addActor(ball);
      }

      @Override
      public void touchDragged(InputEvent event, float x, float y, int pointer) {
        mouseMoved(event, x, y);
      }

      @Override
      public boolean mouseMoved(InputEvent event, float x, float y) {
        float margin = paddle.getWidth() / 2 + walls.getWallSize();
        x = MathUtils.clamp(x, margin, stage.getWidth() - margin);
        paddle.setX(x);
        return true;
      }
    });
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(Gdx.graphics.getDeltaTime());
    stage.draw();

    debugRenderer.draw();
    world.step();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void dispose() {
    LOG.d("Disposing");
    paddle.dispose();
    bricks.dispose();
    walls.dispose();
    debugRenderer.dispose();
    stage.dispose();
    world.dispose();
  }
}
