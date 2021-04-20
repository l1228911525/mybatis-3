package com.liao.mybatisdemo.jdbc;

import java.sql.*;

public class JdbcDemo {

  private static final String SQLITE_DRIVER = "com.mysql.jdbc.Driver";

  private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false";
  private static final String USER_NAME = "root";
  private static final String PASSWORD = "123456";

  private static final String SQL_SELECT = "select name from t_user where id=?";
  private static final String SQL_INSERT = "insert into t_user(id,name) values(?,?)";

  public static void main(String[] args) {

    insert(1, "hahaha");
    String name = selectNameById(1);
    System.out.println(name);
  }

  /**
   * 获取JDBC连接
   */
  private static Connection getConnection() {
    Connection conn = null;


    try {
      Class.forName(SQLITE_DRIVER);
      conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  private static Integer insert(Integer id, String name) {

    Connection connection = getConnection();

    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = connection.prepareStatement(SQL_INSERT);
      preparedStatement.setInt(1, id);
      preparedStatement.setString(2, name);
      return preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return -1;
  }

  private static String selectNameById(Integer id) {
    String name = null;
    Connection connection = getConnection();
    PreparedStatement statement = null;


    try {
      statement = connection.prepareStatement(SQL_SELECT);
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next()) {
        name = resultSet.getString("name");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        statement.close();
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return name;
  }

}
