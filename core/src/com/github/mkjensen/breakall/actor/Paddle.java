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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.github.mkjensen.breakall.World;

public class Paddle extends Box2DActor {

  private static final float WIDTH_FACTOR = 0.1f;
  private static final float HEIGHT_FACTOR = 0.025f;

  public Paddle(World world) {
    super(world, world.getSize() * WIDTH_FACTOR, world.getSize() * HEIGHT_FACTOR);
  }

  @Override
  protected void draw(ShapeRenderer renderer) {
    renderer.begin(ShapeType.Filled);
    renderer.setColor(Color.RED);
    renderer.rect(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    renderer.end();
  }

  @Override
  protected Body createBody(World world) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyType.KinematicBody;
    bodyDef.position.set(world.getSize() / 2, getHeight() / 2);

    PolygonShape rectangle = new PolygonShape();
    rectangle.setAsBox(getWidth() / 2, getHeight() / 2);

    Body body = world.createBody(bodyDef);
    body.createFixture(rectangle, 0f);
    rectangle.dispose();

    return body;
  }
}
