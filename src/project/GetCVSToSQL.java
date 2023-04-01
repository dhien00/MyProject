package project;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class GetCVSToSQL {
	public static void main(String[] args) throws IOException, SQLException {
			String JDBC_DRIVER = "jdbc:sqlserver://192.168.61.130:1433;databaseName=jdbc;user=sa;password=%SQL_SERVER2017%;trustServerCertificate=true";
			String SQL = "INSERT INTO TaipeiFlowers (FileID, SpeciesName, FileName, FileContent, FileType) VALUES (?, ?, ?, ?, ?)";			
			Connection sqlconn = DriverManager.getConnection(JDBC_DRIVER);
            PreparedStatement pstmt = sqlconn.prepareStatement(SQL);
            
            //建立資料夾
			File dir = new File("C:\\Users\\bigred\\Desktop\\TaipeiFlowers"); 
			dir.mkdir();
			
	    	String url = "https://data.coa.gov.tw/Service/OpenData/TaipeiFlowerCanlendar.aspx?FOTT=CSV";	    	
	    	URL obj = new URL(url);  
	    	HttpURLConnection conn = (HttpURLConnection) obj.openConnection(); 
	    	
	    	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            PrintWriter writer = new PrintWriter("C:\\Users\\bigred\\Desktop\\TaipeiFlowers\\TaipeiFlowers.txt", "UTF-8");
            String p="";
            
            inputLine = in.readLine(); 
            while ((inputLine = in.readLine()) != null) { 
            	if (inputLine.equals(""))     
            		continue;
            	List<String> f= Arrays.asList(inputLine.split("\\s*,\\s*"));  	
            	if ( ! p.equals(f.get(1)) ) {
            	    p=f.get(1);
            	    System.out.println(p);
          	        writer.println(inputLine);
            	    try {  
            	    	// 設置參數值
            	    	pstmt.setString(1, f.get(4)); // FileID                        
            	    	pstmt.setString(2, f.get(1)); // SpeciesName            	    	                  
                        pstmt.setString(3, f.get(3)); // FileName     
                        InputStream inp = new URL(f.get(0)).openStream();  //FileContet
                        pstmt.setBinaryStream(4, inp);                        
                        pstmt.setString(5, "jpg"); // FileType
                        
                        pstmt.executeUpdate();
                        System.out.println("Image inserted to SQL Server.");
                        
                    }catch (Exception e) {
                    	System.out.print(e.getMessage());
                    }
            	}    	
            }
            sqlconn.close();
            writer.flush();
            in.close();
            writer.close();          
	}
}
