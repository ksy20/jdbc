package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect2 {

	public static void main(String[] args) { 
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속 성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
		    query +=" select author_id id, ";//컬럼명 정해줬을때
		    query +="        author_name, ";
		    query +="        author_desc ";
		    query +=" from author ";
		    System.out.println(query);
		    
		    //문자열 쿼리문
		    pstmt = conn.prepareStatement(query);
		    
		    //바인딩 생략(넣어줄 값없으니까)

		    rs = pstmt.executeQuery();
		    
		    // 4.결과처리
		    while(rs.next()) {
//		    	int authorId = rs.getInt("id");
//		    	String authorName = rs.getString("author_name");
//		    	String authorDesc = rs.getString("author_desc");
		    	
		    	int authorId = rs.getInt(1);
		    	String authorName = rs.getString(2);
		    	String authorDesc = rs.getString(3);
		    	
		    	AuthorVo author = new AuthorVo(authorId,authorName,authorDesc);
		    	
		    	authorList.add(author);
		    	
		    	//System.out.println(authorId+", "+authorName+", "+authorDesc);
		    }
		    
		    //출력
		    
		    for (int i =0; i<authorList.size(); i++) {
		    	AuthorVo authorVo = authorList.get(i);
		    	System.out.println(authorVo.getAuthorid()+", "+authorVo.getAuthorName()+", "+authorVo.getAuthorDesc());
		    }
		    
		    //첫번쨰 작가 출력
		    AuthorVo authorVo = authorList.get(0);
		    System.out.println(authorVo.getAuthorName());
		    

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

	}

}
