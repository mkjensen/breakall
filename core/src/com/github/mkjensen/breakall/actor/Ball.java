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
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.github.mkjensen.breakall.World;

public class Ball extends Box2DActor {

  private static final float SIZE_FACTOR = 0.015f;
  private static final int CIRCLE_SEGMENTS = 10;
  private static final short FILTER_GROUP_INDEX = -1;
  private static final float RESTITUTION = 1f;
  private static final float FRICTION = 0f;

  public Ball(World world) {
    super(world, world.getSize() * SIZE_FACTOR, world.getSize() * SIZE_FACTOR);
  }

  public float getRadius() {
    return getWidth() / 2;
  }

  @Override
  protected void draw(ShapeRenderer renderer) {
    renderer.begin(ShapeType.Filled);
    renderer.setColor(Color.BLUE);
    renderer.circle(getX(), getY(), getRadius(), CIRCLE_SEGMENTS);
    renderer.end();
  }

  @Override
  protected Body createBody(World world) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyType.DynamicBody;

    CircleShape circle = new CircleShape();
    circle.setRadius(getRadius());

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = circle;
    fixtureDef.filter.groupIndex = FILTER_GROUP_INDEX;
    fixtureDef.friction = FRICTION;
    fixtureDef.restitution = RESTITUTION;

    Body body = world.createBody(bodyDef);
    body.setLinearVelocity(4f, 4f); // TODO Relative to world size?
    body.createFixture(fixtureDef);
    // body.setBullet(true); // TODO Bullet?
    circle.dispose();

    return body;
  }
}
