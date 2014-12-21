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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;

public class Logger {

  private final static ObjectMap<String, Logger> LOGGERS = new ObjectMap<String, Logger>();
  private final String tag;

  private Logger(String tag) {
    this.tag = tag;
  }

  public static Logger getLogger(Class<?> clazz) {
    return getLogger(clazz.getSimpleName());
  }

  public static Logger getLogger(String tag) {
    if (tag == null) {
      throw new NullPointerException("tag must not be null");
    }
    Logger logger = LOGGERS.get(tag);
    if (logger == null) {
      logger = new Logger(tag);
      LOGGERS.put(tag, logger);
    }
    return logger;
  }

  public static Level getLevel() {
    return Level.getLevel(Gdx.app.getLogLevel());
  }

  public static void setLevel(Level level) {
    Gdx.app.setLogLevel(level.getCode());
  }

  public void d(String message) {
    Gdx.app.debug(tag, message);
  }

  public void d(String message, Throwable exception) {
    Gdx.app.debug(tag, message, exception);
  }

  public void i(String message) {
    Gdx.app.log(tag, message);
  }

  public void i(String message, Throwable exception) {
    Gdx.app.log(tag, message, exception);
  }

  public void e(String message) {
    Gdx.app.error(tag, message);
  }

  public void e(String message, Throwable exception) {
    Gdx.app.error(tag, message, exception);
  }

  public enum Level {

    NONE(0), ERROR(1), INFO(2), DEBUG(3);

    private final int code;

    private Level(int code) {
      this.code = code;
    }

    private static Level getLevel(int code) {
      switch (code) {
        case 0:
          return NONE;
        case 1:
          return ERROR;
        case 2:
          return INFO;
        case 3:
          return DEBUG;
        default:
          throw new IllegalArgumentException("Illegal code: " + code);
      }
    }

    private int getCode() {
      return code;
    }
  }
}
