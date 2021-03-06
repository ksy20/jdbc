package com.javaex.ex01;

public class BookAllVo {
	private String authorName;
	private String authorDesc;
	private int bookId;
	private String title;
	private String pubs;
	private String pubDate;
	private int authorId;
	
	public BookAllVo() {
	}

	public BookAllVo(int bookId, String title, String pubs, String pubDate, int authorId, String authorName   
		, String authorDesc) {
		this.authorName = authorName;
		this.authorDesc = authorDesc;
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorId = authorId;
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

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "BookAllVo [authorName=" + authorName + ", authorDesc=" + authorDesc + ", bookId=" + bookId + ", title="
				+ title + ", pubs=" + pubs + ", pubDate=" + pubDate + ", authorId=" + authorId + "]";
	}

}
