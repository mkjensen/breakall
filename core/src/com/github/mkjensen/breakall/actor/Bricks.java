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
package com.github.mkjensen.breakall.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import com.github.mkjensen.breakall.World;

public class Bricks extends Group implements Disposable {

  private static final float SIZE_FACTOR = 0.035f;
  private World world;

  public Bricks(World world) {
    this.world = world;
    createBricks();
  }

  private void createBricks() { // TODO Just testing
    float brickSize = world.getSize() * SIZE_FACTOR;
    for (int i = 0; i < 7; i++) {
      Brick brick = new Brick(world, brickSize, brickSize);
      brick.setPosition(2f + i, 0.8f * world.getSize());
      addActor(brick);
    }
  }

  @Override
  public void dispose() {
    for (Actor actor : getChildren()) {
      Brick brick = (Brick) actor;
      brick.dispose();
    }
  }
}
