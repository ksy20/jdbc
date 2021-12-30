package com.javaex.ex06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.ex01.BookAllVo;

public class BookDao {

	//필드
	
	//생성자
	public BookDao() {
	}
	
	//메소드gs
	
	//메소드 일반
	
	//책 등록
	public void bookInsert(String title, String pubs, String pubDate, int authorId) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속 성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			//문자열
			String query = "";
			
			query +=" INSERT INTO book ";
			query +=" VALUES (SEQ_BOOK_ID.nextval, ?,?,?,?) ";
			
			//문자열 쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			
			//실행
			int count = pstmt.executeUpdate();
		    
		    // 4.결과처리
			System.out.println(count+" 건이 저장되었습니다.");

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

	
	//책 수정
	public void bookUpdate(String title, String pubs, String pubDate, int authorId, int bookId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속 성공");

		    // 3. SQL문 준비 / 바인딩 / 실행
			//문자열
			String query ="";
			
			query+= " update book ";
			query+= " SET title = ?, ";
			query+= " pubs = ?, ";
			query+= " pub_date = ?, ";
			query+= " author_id = ? ";
			query+= " where book_id = ? ";
			
			//문자열 쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pubDate);
			pstmt.setInt(4, authorId);
			pstmt.setInt(5, bookId);
			
			//실행
			int count = pstmt.executeUpdate();
		    
		    // 4.결과처리
			System.out.println(count+" 건이 수정되었습니다.");

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
	
	//책 삭제
	public void bookDelete(int bookId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

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
			
			query+=" DELETE from book ";
			query+=" where book_id = ? ";
			
			//문자열 쿼리문
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setInt(1, bookId);
			
			//실행
			int count = pstmt.executeUpdate();
		    
		    // 4.결과처리
			System.out.println(count+" 건이 삭제되었습니다.");

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
	
	//리스트 불러오기
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
			//문자열 만들기
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
						
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
						
			//바인딩 -->생략  ?표 없음
						
			rs = pstmt.executeQuery();
			
			//실행
			while (rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);
				String pubDate = rs.getString(4);
				int authorId = rs.getInt(5);
				String authorName = rs.getString(6);
				String authorDesc = rs.getString(7);
				
				BookVo bookAll = new BookVo(bookId,title,pubs,pubDate,authorId,authorName,authorDesc);
				bookList.add(bookAll);
			}
			
			for (int i =0; i<bookList.size(); i++) {
				BookVo bookAllVo = bookList.get(i); 
				System.out.println(bookAllVo.getBookId()+","+bookAllVo.getTitle()+","+bookAllVo.getPubs()+","+bookAllVo.getPubDate()+","+bookAllVo.getAuthorId()+","+bookAllVo.getAuthorName()+","+bookAllVo.getAuthorDesc());
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
	
	
}
