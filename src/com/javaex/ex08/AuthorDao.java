package com.javaex.ex08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.ex04.AuthorVo;

public class AuthorDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	public void getConnection() {
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
	    Class.forName(driver);

		// 2. Connection 얻어오기
		conn = DriverManager.getConnection(url, id, pw);
		
		} catch (ClassNotFoundException e) {
			
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			
		} catch (SQLException e) {
			
			System.out.println("error:" + e);
		}
	}
	
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) { rs.close(); 
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

	// 작가 추가
	public void authorInsert(AuthorVo authorVo) {

		//로딩 , connection 가져오기
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// 문자열 만들기
			String query = "";
			query += " insert into author ";
			query += " values(seq_author_id.nextval, ?, ? ) ";
			//System.out.println(query);

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, authorVo.getAuthorName()); // 첫번째 물음표의 데이터
			pstmt.setString(2, authorVo.getAuthorDesc()); // 두번째 물음표의 데이터

			// 실행
			int count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.(작가)");

		}  catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//자원닫기
		close();

	}
	
	
	//select

}
