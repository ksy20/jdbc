package com.javaex.ex03;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		List<AuthorVo>list;
		AuthorDao authorDao = new AuthorDao();
		
		//작가 등록
		AuthorVo vo01 = new AuthorVo("이문열", "경북 영양");
		AuthorVo vo02 = new AuthorVo("박경리", "경상남도 통영");
		
		
		//리스트 가져오기
		System.out.println("----------------------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("----------------------------------");
		
	}

}
