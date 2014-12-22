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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Disposable;

public class World implements Disposable {

  private static final float SIZE = 10f;
  private static final Vector2 GRAVITY = new Vector2(0f, 0f);
  private static final boolean SIMULATE_INACTIVE_BODIES = false;
  private static final float TIME_STEP = 1 / 45f;
  private static final int VELOCITY_ITERATIONS = 6;
  private static final int POSITION_ITERATIONS = 2;
  private final com.badlogic.gdx.physics.box2d.World box2DWorld;

  public World() {
    box2DWorld = new com.badlogic.gdx.physics.box2d.World(GRAVITY, !SIMULATE_INACTIVE_BODIES);
    box2DWorld.setContactListener(new Box2DContactListener());
  }

  @Override
  public void dispose() {
    box2DWorld.dispose();
  }

  public float getSize() {
    return SIZE;
  }

  public Body createBody(BodyDef def) {
    return box2DWorld.createBody(def);
  }

  public void destroyBody(Body body) {
    box2DWorld.destroyBody(body);
  }

  public void step() {
    // https://github.com/libgdx/libgdx/wiki/box2d#stepping-the-simulation
    box2DWorld.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
  }

  com.badlogic.gdx.physics.box2d.World getBox2DWorld() {
    return box2DWorld;
  }
}
