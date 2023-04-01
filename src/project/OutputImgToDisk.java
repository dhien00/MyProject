package project;

import java.io.FileOutputStream;
import java.sql.*;
import java.util.Scanner;

public class OutputImgToDisk {
	private static final String DB_URL = "jdbc:sqlserver://192.168.61.130:1433;databaseName=jdbc;trustServerCertificate=true";
	private static final String USER = "sa";
	private static final String PASSWORD = "%SQL_SERVER2017%";
	private static final String SQL = "SELECT * FROM TaipeiFlowers WHERE FileID = ?";
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in); 
		System.out.println("請輸入 FileID："); 
		String n = sc.next();
		sc.close();	
		Connection conn = null;		
		
        try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, n);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String outFile = "C:\\Users\\bigred\\Desktop\\TaipeiFlowers\\" + rs.getString("FileName");
				FileOutputStream fos = new FileOutputStream(outFile);
				Blob b = rs.getBlob("FileContent"); 
				byte[] data = b.getBytes(1, (int)b.length());
				fos.write(data);
				fos.close();
				System.out.println("File output is successful!");
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
