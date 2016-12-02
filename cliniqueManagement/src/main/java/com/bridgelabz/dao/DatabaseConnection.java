package com.bridgelabz.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseConnection 
{

	public Statement connection()
	{
		Statement stmt = null;
		try
		{
			// Initializing Predefined Properties class and making object of the same
			Properties prop = new Properties();
			String propFileName = "/home/bridgeit/clinique/cliniqueManagement/src/jdbc.properties";
			// passing propFileName to the FileInputStream class object 
			FileInputStream fis = new FileInputStream(propFileName);
			// calling load method of Properties class
			prop.load(fis);
			// ...............get the property value and store it into variable............
			String jdbcDriver = prop.getProperty("jdbc.driverClassName");
			
			String jdbcURL = prop.getProperty("jdbc.url");
			String jdbcUsernName = prop.getProperty("jdbc.username");
			String jdbcPassWord = prop.getProperty("jdbc.password");
			Class.forName(jdbcDriver);

			Connection conn = DriverManager.getConnection(jdbcURL,jdbcUsernName,jdbcPassWord);
			 stmt = conn.createStatement();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return stmt;
	}
}
