package com.revature.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionUtil {
private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	
	private static Connection conn = null;
	// Singleton class
	// ONLY ONE INSTANCE OF THE CLASS
	// CANNOT INSTANTIATE
	// INSTANCE = CONNECTION to the database
	// Call the getInstance() method... 
	
	// 1. Constructor is private
	private ConnectionUtil() {
		super();
	}
	
	/*
	 * comes from a Java SQL file
	 * if it exists return the instance
	 * if not create it (instantiate)
	 * 
	 */
	
	public static Connection getConnection() {
		
		// Check if connection exists
		
		try {
			if(conn != null && !conn.isClosed()) {
			
			return conn;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// how to read the properties file
//		Properties prop = new Properties();
		
		String url = ""; // retreaving the value of the url key
		String username = "";
		String password = "";
		
		// pass in url, username, password
		try {
			// if we can locate the file--then we can go forward
			// 
//			prop.load(new FileReader("C:\\Users\\johnblalock\\Desktop\\PROJECT-1-ORM\\RefelctionTestFirst\\src\\main\\resources\\application.properties"));
			Properties props = new Properties();
			String appDir = new File("").getAbsolutePath();
			String fileName = "application.properties";
			java.nio.file.Path path = java.nio.file.Paths.get(appDir, "src", "main", "resources", fileName);
			boolean fileExists = java.nio.file.Files.exists(path);
			if(!fileExists) {
				System.out.println("file does not exist");
			} 
		       String pathString = path.toString();
		        try (FileReader file = new FileReader(pathString)) {
		            props.load(file);

		        } catch (IOException e) {
//		            log.error(e);
		        }
			url = props.getProperty("url"); // retreaving the value of the url key
			System.out.println("url: " + url);
			
			username = props.getProperty("username");
			password = props.getProperty("password");
			
			
			
			conn = DriverManager.getConnection(url, username, password);
//			System.out.println("You established a connection!!!");
			
			logger.info("DB connection established");
			
			/*
			 * when the getConnection() is called
			 * the DriverManager will attempt to locate a driver
			 * looks for the postgress dependency in the POM file
			 */
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQL Exception thrown - Cannot establish DB connection");
			
			
			System.out.println("cannot establish DB connection");
	
			
			e.printStackTrace();
			
		}
		return conn; // returns if connection established
		
	}
	
}
