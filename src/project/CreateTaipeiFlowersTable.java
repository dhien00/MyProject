package project;

import java.sql.*;

public class CreateTaipeiFlowersTable {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://192.168.61.130:1433;databaseName=jdbc;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "%SQL_SERVER2017%";

	public static void main(String[] args) throws Exception {
		Connection conn=null;
	    try {
	  		Class.forName(JDBC_DRIVER);
	  		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	  		Statement stmt = conn.createStatement();
	  		  		
	        String sql ="drop table if exists TaipeiFlowers;" + 
	                    "CREATE TABLE TaipeiFlowers " +
	                    "(FileID varchar(255) PRIMARY KEY, " +
	                    " SpeciesName nvarchar(255) NOT NULL, " + 
	                    " FileName varchar(255) NOT NULL, " + 
	                    " FileContent varbinary(max) NOT NULL, " + 
	                    " FileType varchar(255) NOT NULL )"; 

	        stmt.execute(sql);
	        System.out.println("Table Created");
	        conn.close();
	    } catch (SQLException e) {
			e.printStackTrace();
	    }
	}
}
