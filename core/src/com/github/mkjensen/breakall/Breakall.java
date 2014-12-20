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

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.mkjensen.breakall.actor.Ball;
import com.github.mkjensen.breakall.actor.Bricks;
import com.github.mkjensen.breakall.actor.Paddle;
import com.github.mkjensen.breakall.actor.Walls;

public class Breakall extends ApplicationAdapter {

  public static final boolean DEBUG = true;
  private DebugRenderer debugRenderer;
  private World world;
  private Stage stage;
  private Walls walls;
  private Bricks bricks;
  private Paddle paddle;

  @Override
  public void create() {
    if (DEBUG) {
      Gdx.app.setLogLevel(Application.LOG_DEBUG);
    }
    world = new World();
    createRenderer();
    createWalls();
    createBricks();
    createPaddle();
  }

  private void createRenderer() {
    debugRenderer = new DebugRenderer();
    FitViewport viewport = new FitViewport(world.getSize(), world.getSize());
    stage = new Stage(viewport);
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

    if (DEBUG) {
      debugRenderer.render(world, stage.getCamera().combined);
    }

    world.step(1 / 45f, 6, 2); // https://github.com/libgdx/libgdx/wiki/box2d#stepping-the-simulation
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void dispose() {
    Gdx.app.debug("Main", "Disposing");
    paddle.dispose();
    bricks.dispose();
    walls.dispose();
    stage.dispose();
    world.dispose();
    debugRenderer.dispose();
  }
}
