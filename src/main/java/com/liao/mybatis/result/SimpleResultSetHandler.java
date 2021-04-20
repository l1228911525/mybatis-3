package com.liao.mybatis.result;



import com.liao.mybatis.binding.MapperMethod;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 拿出结果 然后进行反射賦值
 */
public class SimpleResultSetHandler{


	public <T> T handle(PreparedStatement pstmt, MapperMethod mapperMethod) throws Exception {
		Object resultObj = new ReturnObjectFactory().create(mapperMethod.getType());


		ResultSet rs = pstmt.getResultSet();
		if (rs.next()) {
			int i = 0;
			for (Field field : resultObj.getClass().getDeclaredFields()) {
				setValue(resultObj, field, rs ,i);
			}
		}
		return (T) resultObj;
	}


	private void setValue(Object resultObj, Field field, ResultSet rs, int i) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
		Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
		setMethod.invoke(resultObj, getResult(field,rs));
	}

	private String upperCapital(String name) {
		String first = name.substring(0, 1);
		String tail = name.substring(1);
		return first.toUpperCase() + tail;
	}

	private Object getResult(Field field, ResultSet rs) throws SQLException {
		Class<?> type = field.getType();
		if(Integer.class == type){
			return rs.getInt(field.getName());
		}
		if(String.class == type){
			return rs.getString(field.getName());
		}
		if(Long.class==type){
			return  rs.getLong(field.getName());
		}
		return rs.getString(field.getName());
	}
}
