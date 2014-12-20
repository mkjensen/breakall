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

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;

public class DebugRenderer implements Disposable {

  private Box2DDebugRenderer box2DDebugRenderer;

  public DebugRenderer() {
    box2DDebugRenderer = new Box2DDebugRenderer();
  }

  public Box2DDebugRenderer getBox2DDebugRenderer() {
    return box2DDebugRenderer;
  }

  public void render(World world, Matrix4 projMatrix) {
    box2DDebugRenderer.render(world.getBox2DWorld(), projMatrix);
  }

  @Override
  public void dispose() {
    box2DDebugRenderer.dispose();
  }
}
