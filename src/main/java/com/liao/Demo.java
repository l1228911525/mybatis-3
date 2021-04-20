package com.liao;

import com.liao.dao.AdminDao;
import com.liao.pojo.Admin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Demo {
  public static void main(String[] args) throws IOException {
    //讀取全局配置文件
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    //通過全局配置文件的字節輸入流構建SqlSessionFactory對象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    //打開一個新的SqlSession,相當于是一次對話，使用完需要關閉
    //表示調用哪條sql語句，SQL語句需要傳遞的參數
    SqlSession sqlSession = sqlSessionFactory.openSession();
    //获取接口的动态代理对象
    AdminDao adminDao = sqlSession.getMapper(AdminDao.class);

    System.out.println("第一次查询");
    //调用接口中的方法
    Admin admin = adminDao.getAdminById(1);

    System.out.println("第二次查询");
    Admin admin2 = adminDao.getAdminById(1);

    System.out.println(admin == admin2);

    //List<Country> country = countryDao.getAllCountry();
    System.out.println(admin);
    sqlSession.close();
  }

  private SqlSession session;
  private SqlSessionFactory sqlSessionFactory;

  @Before
  public void init() throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    session = sqlSessionFactory.openSession(true);
  }

  @Test
  public void testCache() {
    System.out.println("test test cache");

    String str = "234";

    int sum = 0;
    int product = 1;

    for(int i = 0; i < str.length(); ++i) {

      char c = str.charAt(i);

      int a = Integer.parseInt(String.valueOf(c));

      sum += a;
      product *= a;

    }

    System.out.println(product - sum);
  }

}
