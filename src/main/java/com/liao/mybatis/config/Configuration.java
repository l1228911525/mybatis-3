package com.liao.mybatis.config;

import com.liao.mybatis.binding.MapperMethod;
import com.liao.mybatis.binding.MapperRegistry;
import com.liao.mybatis.utils.JdbcUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取xml加载到内存中
 */
public class Configuration {
    private ClassLoader classLoader;
    private String xmlConfigPath;
    private JdbcUtils jdbcUtils = new JdbcUtils();
    private MapperRegistry mapperRegistry=new MapperRegistry();

    /**通过dom4j读取全局配置文件*/
    public Configuration(ClassLoader classLoader, String xmlConfigPath) {
        this.classLoader = classLoader;
        this.xmlConfigPath = xmlConfigPath;
        init();
    }

    public void init(){
        try (InputStream inputStream = classLoader.getResourceAsStream(xmlConfigPath)) {
            Document document = new SAXReader().read(inputStream);
            Element root = document.getRootElement();
            jdbcConfig(root);
            mappersConfig(root);
        } catch (Exception e) {
            System.out.println("读取配置文件错误!");
        }
    }




    private void jdbcConfig(Element root) {
        Element environments = root.element("environments");
        String defaultId = environments.attributeValue("default");
        List<Element> environmentList = environments.elements("environment");
        for (Element environment : environmentList) {
            if (defaultId.equalsIgnoreCase(environment.attributeValue("id"))){
                Element dataSourceElement = environment.element("dataSource");
                List<Element> propertyList = dataSourceElement.elements("property");
                for (Element propertyElement : propertyList) {
                    if ("driver".equalsIgnoreCase(propertyElement.attributeValue("name"))){
                        try {
                            Class.forName(propertyElement.attributeValue("value"));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if ("url".equalsIgnoreCase(propertyElement.attributeValue("name"))){
                        String url = propertyElement.attributeValue("value");
                        jdbcUtils.setUrl(url);
                    }
                    if ("username".equalsIgnoreCase(propertyElement.attributeValue("name"))){
                        jdbcUtils.setUsername(propertyElement.attributeValue("value"));
                    }
                    if ("password".equalsIgnoreCase(propertyElement.attributeValue("name"))){
                        jdbcUtils.setPassword(propertyElement.attributeValue("value"));
                    }
                }
            }
        }
    }

    public JdbcUtils getJdbcUtils() {
        return jdbcUtils;
    }



    private void mappersConfig(Element root) throws IOException, DocumentException {
        List<Element> mappers = root.element("mappers").elements("mapper");
        for (Element mapper : mappers) {
            if (mapper.attribute("resource") != null) {
                String sqlXmlPath = mapper.attributeValue("resource");
                try (InputStream is = this.classLoader.getResourceAsStream(sqlXmlPath)) {
                    Document document = new SAXReader().read(is);
                    Element mapperRoot = document.getRootElement();
                    Map<String, MapperMethod> knownMappers = mapperMethodConfig(mapperRoot);
                    mapperRegistry.setKnownMappers(knownMappers);
                }
            }
        }
    }


    /**配置映射文件*/
    private Map<String, MapperMethod> mapperMethodConfig(Element root) {
        Map<String, MapperMethod> map = new HashMap<>();
        try {
            if ("mapper".equalsIgnoreCase(root.getName())) {
                String namespace = root.attributeValue("namespace");
                List<Element> selectList = root.elements("select");
                for (Element select : selectList) {
                    String sql = select.getText().trim();
                    String selectId = select.attributeValue("id");
                    String resultType = select.attributeValue("resultType");

                    MapperMethod mapperMethod = new MapperMethod();
                    mapperMethod.setSql(sql);
                    mapperMethod.setType(Class.forName(resultType));
                    map.put(namespace + "." + selectId, mapperMethod);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }



    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }
}
