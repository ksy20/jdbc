package com.javaex.ex01;

public class AuthorVo {
	//필드
	private int authorid;
	private String authorName;
	private String authorDesc;
	
	//생성자
	public AuthorVo() {
	}

	public AuthorVo(int authorid, String authorName, String authorDesc) {
		this.authorid = authorid;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
    //메소드gs
	public int getAuthorid() {
		return authorid;
	}

	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}
	
    //메소드 일반
	@Override
	public String toString() {
		return "AuthorVo [authorid=" + authorid + ", authorName=" + authorName + "]";
	}
	
	
	
	
	
	
	
	

}
