package com.liao.mybatis.session;


import com.liao.mybatis.config.Configuration;
import com.liao.mybatis.executor.SimpleExecutor;


public class SqlSessionFactory {

    private Configuration configuration;

    public SqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession() {
        return  new SqlSession(configuration, new SimpleExecutor(configuration));
    }

}
