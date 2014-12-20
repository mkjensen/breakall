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

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import com.github.mkjensen.breakall.World;

public class Walls extends Group implements Disposable {

  private static final float WALL_SIZE_FACTOR = 0.025f;
  private World world;
  private float wallSize;
  private Wall bottomLeft;
  private Wall left;
  private Wall topLeft;
  private Wall top;
  private Wall topRight;
  private Wall right;
  private Wall bottomRight;

  public Walls(World world) {
    this.world = world;
    this.wallSize = world.getSize() * WALL_SIZE_FACTOR;
    createWalls();
  }

  @Override
  public void dispose() {
    removeRight();
    removeTop();
    removeLeft();
    removeCorners();
  }

  public float getWallSize() {
    return wallSize;
  }

  private void createWalls() {
    addCorners();
    addLeft();
    addTop();
    addRight();
  }

  private void addCorners() {
    float halfWallSize = wallSize / 2;
    float worldSizeMinusHalfWallSize = world.getSize() - halfWallSize;
    bottomLeft = addCorner(halfWallSize, halfWallSize);
    topLeft = addCorner(halfWallSize, worldSizeMinusHalfWallSize);
    topRight = addCorner(worldSizeMinusHalfWallSize, worldSizeMinusHalfWallSize);
    bottomRight = addCorner(worldSizeMinusHalfWallSize, halfWallSize);
  }

  private Wall addCorner(float x, float y) {
    Wall wall = new Wall(world, wallSize, wallSize);
    wall.setPosition(x, y);
    addActor(wall);
    return wall;
  }

  private void removeCorners() {
    remove(bottomRight);
    remove(topRight);
    remove(topLeft);
    remove(bottomLeft);
  }

  public void addLeft() {
    if (left == null) {
      left = new Wall(world, wallSize, world.getSize() - 2 * wallSize);
      left.setPosition(wallSize / 2, world.getSize() / 2);
      addActor(left);
    }
  }

  public void removeLeft() {
    remove(left);
    left = null;
  }

  public void addTop() {
    top = new Wall(world, world.getSize() - 2 * wallSize, wallSize);
    top.setPosition(world.getSize() / 2, world.getSize() - wallSize / 2);
    addActor(top);
  }

  public void removeTop() {
    remove(top);
    top = null;
  }

  public void addRight() {
    right = new Wall(world, wallSize, world.getSize() - 2 * wallSize);
    right.setPosition(world.getSize() - wallSize / 2, world.getSize() / 2);
    addActor(right);
  }

  public void removeRight() {
    remove(right);
    right = null;
  }

  private void remove(Wall wall) {
    if (wall != null) {
      wall.remove();
      wall.dispose();
    }
  }
}
