package com.liao.mybatis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
	private String url;
	private String username;
	private String password;

	public Connection openConnection() {
		try {
			return DriverManager.getConnection(url,username, password);
		} catch (Exception e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
