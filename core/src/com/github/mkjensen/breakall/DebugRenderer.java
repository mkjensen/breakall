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

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;

public class DebugRenderer implements Disposable {

  private final Box2DDebugRenderer box2DDebugRenderer;
  private final World world;
  private final Camera camera;
  private boolean enabled;

  public DebugRenderer(World world, Camera camera) {
    this.box2DDebugRenderer = new Box2DDebugRenderer();
    this.world = world;
    this.camera = camera;
    this.enabled = true;
  }

  @Override
  public void dispose() {
    box2DDebugRenderer.dispose();
  }

  public void draw() {
    if (enabled) {
      box2DDebugRenderer.render(world.getBox2DWorld(), camera.combined);
    }
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
