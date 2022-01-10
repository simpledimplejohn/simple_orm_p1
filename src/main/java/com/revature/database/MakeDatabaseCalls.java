package com.revature.database;

import java.lang.annotation.Annotation;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.revature.annotations.Id;

import com.revature.annotations.Column;
import com.revature.inspection.ClassInspector;
import com.revature.util.ConnectionUtil;

public class MakeDatabaseCalls {

	private static Logger logger = Logger.getLogger(MakeDatabaseCalls.class);

	static ClassInspector CI = new ClassInspector();

	public static String createSqlString(Class<?> clazz) {

		// get table name
		String tableName = CI.getClassTableName(clazz);
		// table name to sql string
		String sqlString = "CREATE TABLE " + tableName + " (";
		// get all fields
		Field[] fields = clazz.getDeclaredFields();
		String[] fieldArray = new String[fields.length];
		String[] nameArray = new String[fields.length];
		// populate fieldArray and nameArray
		for (int i = 0; i < fields.length; i++) {
			nameArray[i] = returnColumnAnnotations(fields[i].getAnnotations());
			if (fields[i].getType().equals(Boolean.TYPE)) {
				fieldArray[i] = "bool";
			}
			if (fields[i].getType().equals(Integer.TYPE)) {
				fieldArray[i] = "int";
			}

			if (fields[i].getType().equals(String.class)) {
				fieldArray[i] = "VARCHAR";
			}
		}
		// add each name and type to string
		for (int i = 0; i < fields.length; i++) {
			if (i == fields.length - 1) {
				sqlString += " " + nameArray[i] + " " + fieldArray[i];				
			} else {
				sqlString += " " + nameArray[i] + " " + fieldArray[i] + ",";
			}
		}

		sqlString += ")";		
		return sqlString;
	}

	public static String createTableFromClass(Class<?> clazz) {
		// get table name
		String tableName = CI.getClassTableName(clazz);
		// table name to sql string
		String sqlString = "CREATE TABLE " + tableName + " (";
		// get all fields
		Field[] fields = clazz.getDeclaredFields();
		String[] fieldArray = new String[fields.length];
		String[] nameArray = new String[fields.length];
		// populate fieldArray and nameArray
		for (int i = 0; i < fields.length; i++) {
			nameArray[i] = returnColumnAnnotations(fields[i].getAnnotations());
			if (fields[i].getType().equals(Boolean.TYPE)) {
				fieldArray[i] = "bool";
			}
			if (fields[i].getType().equals(Integer.TYPE)) {
				fieldArray[i] = "int";
			}

			if (fields[i].getType().equals(String.class)) {
				fieldArray[i] = "VARCHAR";
			}
		}
		// add each name and type to string
		for (int i = 0; i < fields.length; i++) {
			if (i == fields.length - 1) {
				sqlString += " " + nameArray[i] + " " + fieldArray[i];				
			} else {
				sqlString += " " + nameArray[i] + " " + fieldArray[i] + ",";
			}
		}

		sqlString += ")";
		
		try {
			Connection conn = ConnectionUtil.getConnection();

			PreparedStatement stmt = conn.prepareStatement(sqlString);

			stmt.execute();

		} catch (SQLException e) {
			logger.error("Unable to make table - SQL exception found");
			e.printStackTrace();
		}
		
		return sqlString;
	}

	public String ifIdPresent(Class<?> clazz) {
		String idString = "";

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			idString += returnIdFromFields(field.getAnnotations());
		}

		return idString;
	}

	public String returnIdFromFields(Annotation[] annotations) {
		String Idstring = "";

		for (Annotation annotation : annotations) {
			if (annotation.annotationType().equals(Id.class)) {
				Idstring += "SERIAL primary key";
			}
		}

		return Idstring;

	}

	/**
	 * Analyzes the set of
	 */
	public static String returnColumnAnnotations(Annotation[] annotations) {

		StringBuilder builder = new StringBuilder();
		for (Annotation annotation : annotations) {
			if (annotation.annotationType().equals(Column.class)) {

				builder.append(((Column) annotation).columnName());
			}
		}

		return builder.toString();
	}

/////////////////////////////////////////////////////////////////////	

	public static void MakeTableReflection(Class<?> clazz) {

		String tableName = CI.getClassTableName(clazz);
		System.out.println("table name here " + tableName);

		Field[] fields = CI.listFields(clazz);

		try {
			Connection conn = ConnectionUtil.getConnection();

			String sql = "CREATE TABLE " + tableName + " ();";

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
