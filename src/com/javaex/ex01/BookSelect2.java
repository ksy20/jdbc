package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelect2 {

	public static void main(String[] args) {
		
		List<BookVo> bookList = new ArrayList<BookVo>();
		
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
			//문자열 만들기
			String query ="";
			
			query+= " select book_id, ";
			query+= "        title, ";
			query+= "        pubs, ";
			query+= "        pub_date, ";
			query+= "        author_id ";
			query+= " from book ";
			System.out.println(query);
			
			//문자열 쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩 생략
			rs = pstmt.executeQuery();
			
			//실행
			while(rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);
				String pubDate = rs.getString(4);
				int authorId = rs.getInt(5);
				
				BookVo book = new BookVo(bookId,title,pubs,pubDate,authorId);
				
				bookList.add(book);
				
				}
			
			for(int i =0; i<bookList.size(); i++) {
				BookVo bookVo = bookList.get(i);
				System.out.println(bookVo.getBookId()+", "+bookVo.getTitle()+", "+bookVo.getPubs()+", "+bookVo.getPubDate()+", "+bookVo.getAuthorId());
			}
		    
		    // 4.결과처리

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
