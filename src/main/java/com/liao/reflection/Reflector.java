package com.liao.reflection;

import com.liao.Demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Reflector {

  private final Class<?> type;

  private final String[] readablePropertyNames;

  private final String[] writeablePropertyNames;

  private final Map<String, Method> setMethods = new HashMap<>();
  private final Map<String, Method> getMethods = new HashMap<>();
  private final Map<String, Class<?>> setTypes = new HashMap<>();
  private final Map<String, Class<?>> getTypes = new HashMap<>();

  private Constructor<?> defaultConstrctor;

  private Map<String, String> caseInsensitivePropertyMap = new HashMap<>();

  public Reflector(Class<?> clazz) {
    readablePropertyNames = null;
    writeablePropertyNames = null;
    type = clazz;
    addDefaultConstructor(clazz);

  }

  private void addDefaultConstructor(Class<?> clazz) {

    Constructor<?>[] consts = clazz.getDeclaredConstructors();

    for (Constructor<?> constructor :consts){

      if(0 == constructor.getParameterTypes().length) {
        if(constructor.isAccessible()) {
          this.defaultConstrctor = constructor;
        }
      }

    }
  }

  public static void main(String[] args) throws Exception {
    Reflector reflector = new Reflector(Demo.class);
    Demo demo = (Demo)reflector.defaultConstrctor.newInstance();
    demo.testCache();
  }

}
