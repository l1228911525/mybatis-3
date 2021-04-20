package com.liao.mybatis.binding;

/***
 * 把我们解析的sql（UserMapper）加载到这个类中
 */
public class MapperMethod<T> {
    private String sql;
    private Class<T> type;

    public MapperMethod(String sql, Class<T> type) {
        this.sql = sql;
        this.type = type;
    }
    public MapperMethod() {

    }
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}
