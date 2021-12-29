package com.javaex.ex05;

public class BookApp {

	public static void main(String[] args) {
		BookDao bookDao = new BookDao();
		
		//북등록
		bookDao.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert("토지", "마로니에북스", "2012-08-15", 2);
	}

}
