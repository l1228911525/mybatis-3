package com.liao.mybatis.binding;

import java.util.HashMap;
import java.util.Map;

public class MapperRegistry {

    private Map<String, MapperMethod> knownMappers = new HashMap<>();

    public Map<String, MapperMethod> getKnownMappers() {
        return knownMappers;
    }

    public void setKnownMappers(Map<String, MapperMethod> knownMappers) {
        this.knownMappers = knownMappers;
    }
}


