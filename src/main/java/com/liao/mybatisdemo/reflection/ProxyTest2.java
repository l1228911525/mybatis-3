package com.liao.mybatisdemo.reflection;

public class ProxyTest2 {
  public static void main(String[] args) {
    SubjectProxy2<Subject2> subjectProxy2 = new SubjectProxy2(Subject2.class);
    Subject2 subject2 = subjectProxy2.getProxy();
    subject2.selectById();
  }
}
