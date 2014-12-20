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

import com.github.mkjensen.breakall.World;

public class Brick extends Wall {

  private boolean alive = true;

  public Brick(World world, float width, float height) {
    super(world, width, height);
  }

  @Override
  public void act(float delta) {
    if (!alive) {
      remove();
      dispose();
    }
  }

  @Override
  public void contact(Box2DActor other) {
    alive = false;
  }
}
