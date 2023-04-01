package project;

import java.io.*;
import java.sql.*;

public class InputImgToSQL {
	private static final String DB_URL = "jdbc:sqlserver://192.168.61.130:1433;databaseName=jdbc;trustServerCertificate=true";   // 註冊 JDBC 驅動程式
	private static final String USER = "sa";
	private static final String PASSWORD = "%SQL_SERVER2017%";
	private static final String SQL = "INSERT INTO TaipeiFlowers VALUES(? ,? ,? ,? ,?)";
	
	public static void main(String[] args) throws Exception {	
		Connection conn = null;				
        try {
        	conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);							
        	File f= new File("C:\\Users\\bigred\\Desktop\\202303280000_000001.gif");
        	FileInputStream fis = new FileInputStream(f);
        	BufferedInputStream in = new BufferedInputStream(fis);
        	PreparedStatement pstmt = conn.prepareStatement(SQL); 
		
			pstmt.setInt(1, 1);
			pstmt.setString(2, "菊花");
			pstmt.setString(3, "202303280000_000001.gif");				
			pstmt.setBinaryStream(4, in, (int) f.length());
			pstmt.setString(5, "gif");
			
			int count = pstmt.executeUpdate(); 
			if (count > 0)
				System.out.println("Insert Img to TaipeiFlowers is successful!");				
			pstmt.close();	
			conn.close();
        } catch (SQLException e) {
			e.printStackTrace();
	    }
	}
}
