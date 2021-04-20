package com.liao.mybatisdemo.mybatis.config;

import com.liao.mybatis.config.Configuration;
import com.liao.mybatis.dao.UserMapper;
import com.liao.mybatis.session.SqlSession;
import com.liao.mybatis.session.SqlSessionFactory;
import org.junit.Test;

import java.sql.Connection;

public class ConfigurationTest {


  @Test
  public void testConfiguration() {
    ClassLoader classLoader = ConfigurationTest.class.getClassLoader();
    Configuration configuration = new Configuration(classLoader,"mybatis.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactory(configuration);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    System.out.println(userMapper);



  }
}
