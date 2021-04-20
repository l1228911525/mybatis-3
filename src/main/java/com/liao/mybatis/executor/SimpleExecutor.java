package com.liao.mybatis.executor;


import com.liao.mybatis.binding.MapperMethod;
import com.liao.mybatis.config.Configuration;
import com.liao.mybatis.statement.StatementHandler;

public class SimpleExecutor {
	private Configuration configuration;

	public SimpleExecutor(Configuration configuration) {
		this.configuration = configuration;
	}

	public <T> T query(MapperMethod method, Object param) throws Exception {
		StatementHandler statementHandler = new StatementHandler(configuration);
		return statementHandler.query(method,param);
	}
}

