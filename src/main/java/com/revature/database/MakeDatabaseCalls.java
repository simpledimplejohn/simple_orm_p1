package com.revature.database;

import java.lang.annotation.Annotation;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.annotations.Column;
import com.revature.inspection.ClassInspector;
import com.revature.util.ConnectionUtil;

public class MakeDatabaseCalls {

	private static Logger logger = Logger.getLogger(MakeDatabaseCalls.class);

	static ClassInspector CI = new ClassInspector();
/////// TEST OF SQL STATMENT /////////////////////////////
	
	public static String testSqlString(Class<?> clazz) {
		
		String sqlString = "(";
		
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields) {
			sqlString += generateSqlFromAnnotationSet(field.getAnnotations());
		}
		sqlString += " )";
		
		return sqlString;
	}
	
	
	/**
	 * Analyzes the set of 
	 * */
	public static String generateSqlFromAnnotationSet(Annotation[] annotations) {
		
		StringBuilder builder = new StringBuilder();
		for(Annotation annotation : annotations) {
			if (annotation.annotationType().equals(Column.class)) {
				builder.append(((Column)annotation).columnName());
			}
		}
		
		
		return builder.toString();
	}
	
	
/////////////////////////////////////////////////////////////////////	

	public static void MakeTableReflection(Class<?> clazz) {

		String tableName = CI.getClassTableName(clazz);
		System.out.println(tableName);
		
		Field[] fields = CI.listFields(clazz);
		
		
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "CREATE TABLE " + tableName + " (test_value int);";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.execute();

		} catch (SQLException e) {
			logger.error("Unable to make table - SQL exception found");
			e.printStackTrace();
		}	

	}

	public static int insert(String string) {
		return -1;

	}

}
