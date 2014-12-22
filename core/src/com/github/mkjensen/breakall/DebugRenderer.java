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
  private boolean enabled;

  public DebugRenderer(boolean enabled) {
    box2DDebugRenderer = new Box2DDebugRenderer();
    this.enabled = enabled;
  }

  @Override
  public void dispose() {
    box2DDebugRenderer.dispose();
  }

  public Box2DDebugRenderer getBox2DDebugRenderer() {
    return box2DDebugRenderer;
  }

  public void render(World world, Matrix4 projMatrix) {
    if (enabled) {
      box2DDebugRenderer.render(world.getBox2DWorld(), projMatrix);
    }
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
