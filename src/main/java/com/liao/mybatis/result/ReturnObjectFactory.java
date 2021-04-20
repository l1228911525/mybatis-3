package com.liao.mybatis.result;


public class ReturnObjectFactory {


    public <T> Object create(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


