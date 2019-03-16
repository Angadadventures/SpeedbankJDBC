package com.cg.speedbank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.speedbank.exception.SpeedBankException;

public class ConnectionFactory {
	private static ConnectionFactory sigtonObj;

	public static ConnectionFactory getSigtonObj() {

		if (sigtonObj == null)
			sigtonObj = new ConnectionFactory();

		return sigtonObj;

	}

	public Connection getConnection() throws ClassNotFoundException, SQLException   {
		Connection con = null;
		
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
			String user = "system"; 
			String pwd = "Capgemini123"; 
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
			if(con == null)
				System.out.println("con is null....");
		
		return con;
	
	}
}
