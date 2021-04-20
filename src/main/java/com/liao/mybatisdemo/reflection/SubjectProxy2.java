package com.liao.mybatisdemo.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubjectProxy2<T> implements InvocationHandler {
  private Class<T> proxyInterface;
  //这里可以维护一个缓存，存这个接口的方法抽象的对象

  SubjectProxy2(Class<T> proxyInterface){
    this.proxyInterface = proxyInterface;
  }


  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.getName().equals("selectById")){
      //String result = (String) method.invoke(proxyInterface,args);
      //这里可以得到方法抽象对象来调用真的的查询方法
      System.out.println("selectById调用成功");
    }
    return null;
  }

  public T getProxy(){
    return (T) Proxy.newProxyInstance(proxyInterface.getClassLoader(),new Class[]{proxyInterface},this);
  }
}
