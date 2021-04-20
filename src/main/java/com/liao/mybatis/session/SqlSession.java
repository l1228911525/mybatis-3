package com.liao.mybatis.session;


import com.liao.mybatis.binding.MapperMethod;
import com.liao.mybatis.binding.MapperProxy;
import com.liao.mybatis.config.Configuration;
import com.liao.mybatis.executor.SimpleExecutor;

import java.lang.reflect.Proxy;

public class SqlSession {

	private Configuration configuration;
	private SimpleExecutor simpleExecutor;

	public SqlSession(Configuration configuration, SimpleExecutor simpleExecutor) {
		this.configuration = configuration;
		this.simpleExecutor = simpleExecutor;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public <T> T getMapper(Class<T> type) {
    T t = (T)Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MapperProxy<>(this, type));
    return t;
	}

	public <T> T selectOne(MapperMethod mapperMethod, Object param) throws Exception {
		return  simpleExecutor.query(mapperMethod,param);
	}
}

