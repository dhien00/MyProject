package project;

import java.sql.*;
import java.util.Scanner;

public class DeleteDataInSQL {
	private static final String DB_URL = "jdbc:sqlserver://192.168.61.130:1433;databaseName=jdbc;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "%SQL_SERVER2017%";
	private static final String SQL = "SELECT * FROM TaipeiFlowers WHERE FileID = ?";
	private static final String UPDATE = "DELETE FROM TaipeiFlowers WHERE FileID = ?";
	
	public static void main(String[] args) throws Exception {		
		Scanner sc = new Scanner(System.in); 
		System.out.println("請輸入刪除的資料："); 
		String key = sc.next();
		sc.close();	
		Connection conn = null;
        try {
        	conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(SQL);			
			pstmt.setString(1, key);	
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				pstmt = conn.prepareStatement(UPDATE);
				pstmt.setString(1, key);					
				pstmt.executeUpdate();
				System.out.println(key + " FileID deleted");			
			} else {
				System.out.println("FileID Not Found");
			}
			
			rs.close();			
			pstmt.close();
			conn.close();
        } catch (SQLException e) {
			e.printStackTrace();
	    }		
	}
}
