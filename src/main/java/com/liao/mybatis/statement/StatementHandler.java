package com.liao.mybatis.statement;


import com.liao.mybatis.binding.MapperMethod;
import com.liao.mybatis.config.Configuration;
import com.liao.mybatis.result.SimpleResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StatementHandler {
	private Configuration configuration;
	private SimpleResultSetHandler resultSetHandler = new SimpleResultSetHandler();


	public StatementHandler(Configuration configuration) {
		this.configuration = configuration;
	}


	public <T> T query(MapperMethod method, Object param) throws Exception {
		Connection connection= configuration.getJdbcUtils().openConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(String.format(method.getSql(), Integer.parseInt(String.valueOf(param))));
		preparedStatement.execute();
		return resultSetHandler.handle(preparedStatement,method);
	}
}


