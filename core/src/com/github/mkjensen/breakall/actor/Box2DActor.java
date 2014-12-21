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

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.github.mkjensen.breakall.World;

// TODO Lots of methods in Actor do not make sense because of the Box2D Body
public abstract class Box2DActor extends Actor implements Disposable {

  private World world;
  private Body body;
  private ShapeRenderer renderer;

  protected Box2DActor(World world) {
    this(world, 0f, 0f);
  }

  protected Box2DActor(World world, float width, float height) {
    setWidth(width);
    setHeight(height);
    this.world = world;
    this.body = setupBody();
    this.renderer = new ShapeRenderer();
  }

  private Body setupBody() {
    Body body = createBody(world);
    body.setActive(false);
    body.setUserData(this);
    return body;
  }

  @Override
  public float getX() {
    return body.getPosition().x;
  }

  @Override
  public float getY() {
    return body.getPosition().y;
  }

  @Override
  public void setX(float x) {
    body.setTransform(x, getY(), 0);
  }

  @Override
  public void setY(float y) {
    body.setTransform(getX(), y, 0);
  }

  @Override
  public void setPosition(float x, float y) {
    body.setTransform(x, y, 0);
  }

  @Override
  public void act(float delta) {
    // Acting is done via Box2D.
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.end();
    renderer.setProjectionMatrix(batch.getProjectionMatrix());
    renderer.setTransformMatrix(batch.getTransformMatrix());
    draw(renderer);
    batch.begin();
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    body.setActive(visible);
  }

  @Override
  public void dispose() {
    remove();
    renderer.dispose();
    world.destroyBody(body);
  }

  @Override
  protected void setParent(Group parent) {
    super.setParent(parent);
    body.setActive(parent != null);
  }

  @Override
  protected void setStage(Stage stage) {
    super.setStage(stage);
    body.setActive(stage != null);
  }

  public void contact(Box2DActor other) {}

  protected abstract Body createBody(World world);

  protected abstract void draw(ShapeRenderer renderer);
}
