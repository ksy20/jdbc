package com.javaex.ex08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	public void getConnect() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
		    Class.forName("driver");
		    // 2. Connection 얻어오기
		    conn = DriverManager.getConnection(url, "webdb", "webdb");
		}catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
	public void close() {
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
	
	//insert
	public void bookInsert(BookVo bookVo) {

		try {
			getConnect();

		    // 3. SQL문 준비 / 바인딩 / 실행
		    //문자열
		    String query ="";
		    
		    query +=" INSERT INTO book ";
			query +=" VALUES (SEQ_BOOK_ID.nextval, ?,?,?,?) ";

		    //문자열 쿼리
		    pstmt = conn.prepareStatement(query);

		    //바인딩
		    pstmt.setString(1, bookVo.getTitle());
		    pstmt.setString(2, bookVo.getPubs());
		    pstmt.setString(3, bookVo.getPubDate());
		    pstmt.setInt(4, bookVo.getAuthorId());
		    
		    //실행
		    int count = pstmt.executeUpdate();      //insert, update, delete
		    
		    // 4.결과처리
		    System.out.println(count+" 건이 저장되었습니다.");

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		close();
	}
	
	//select
	public List<BookVo> bookSelect() {
		List<BookVo> bookList = new ArrayList<BookVo>();

		try {
		    getConnect();

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

		    // 4.결과처리

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		close();
		
		return bookList;
	}
	
	//검색
	public List<BookVo> bookSearch() {
		List<BookVo> bookSearch = new ArrayList<BookVo>();
		try {
		    getConnect();

		    // 3. SQL문 준비 / 바인딩 / 실행
		    //문자열
		    String query="";
		    
		    query=" select boo.book_id, ";
		    query="        boo.title, ";
		    query="        boo.pubs, ";
		    query="        boo.pub_date, ";
		    query="        boo.author_id, ";
		    query="        aut.author_name, ";
		    query="        aut.author_desc ";
		    query=" from book boo, author aut ";
		    query=" where boo.author_id = aut.author_id ";
		    query=" and (boo.title, boo.pubs, aut.author_name) ";
		    query=" in (select bo.title, ";
		    query="            bo.pubs, ";
		    query="            au.author_name ";
		    query="     from author au, book bo";
		    query="     where bo.title like '%문%' ";
		    query="     or bo.pubs like '%문%' ";
		    query="     or au.author_name like '%문%') ";

		    //문자열 쿼리
		    pstmt = conn.prepareStatement(query);

		    //바인딩
		    
		    //실행
		    rs = pstmt.executeQuery();      //insert, update, delete

		    // 4.결과처리
		    System.out.println();

		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		close();
		return bookSearch();
	}

}
