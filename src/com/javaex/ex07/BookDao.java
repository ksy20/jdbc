package com.javaex.ex07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BookDao {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	public BookDao() { // 생략가능
	}

	//insert
	public void bookInsert(BookVo bookVo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
		    Class.forName(driver);
		    // 2. Connection 얻어오기
		    conn = DriverManager.getConnection(url, id, pw);

		    // 3. SQL문 준비 / 바인딩 / 실행
		    //문자열
		    String query ="";
		    
		    query +=" INSERT INTO book ";
			query +=" VALUES (SEQ_BOOK_ID.nextval, ?,?,?,?) ";

		    //문자열 쿼리
		    pstmt = conn.prepareStatement(query);

		    //바인딩
		    pstmt.setInt(1, bookVo.getBookId());
		    pstmt.setString(2, bookVo.getTitle());
		    pstmt.setString(3, bookVo.getPubs());
		    pstmt.setString(4, bookVo.getPubDate());
		    pstmt.setInt(5, bookVo.getAuthorId());
		    
		    //실행
		    int count = pstmt.executeUpdate();      //insert, update, delete

		    // 4.결과처리
		    System.out.println(count +" 건이 저장되었습니다.");

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {              
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
	
	//select
	public List<BookVo> bookSelect() {
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

		    // 3. SQL문 준비 / 바인딩 / 실행
		    //문자열
		    String query ="";
			
			query+="select bo.book_id, ";
			query+="       bo.title, ";
			query+="       bo.pubs, ";
			query+="       to_char(bo.pub_date,'yyyy-mm-dd' ), ";
			query+="       bo.author_id, ";
			query+="       au.author_name, ";
			query+="       au.author_desc ";
			query+="from book bo, author au ";
			query+="where bo.author_id = au.author_id ";

		    //문자열 쿼리
		    pstmt = conn.prepareStatement(query);

		    //바인딩
		   
		    
		    //실행

		    rs = pstmt.executeQuery();    //select
		    
		    while(rs.next()) {
		    	int bookId = rs.getInt("book_id");
		    	String title = rs.getString("title");
		    	String pubs = rs.getString("pubs");
		    	String pubDate = rs.getString("pub_date");
		    	int authorId = rs.getInt("author_id");
		    	String authorName = rs.getString("author_name");
		    	String authorDesc = rs.getString("author_desc");
		    	
		    	BookVo vo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
		    
		    	bookList.add(vo);
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

		return bookList;
	}
	
	//delete
	
	//update

}
