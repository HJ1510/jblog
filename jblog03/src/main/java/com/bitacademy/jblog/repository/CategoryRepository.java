package com.bitacademy.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(CategoryVo categoryVo) {
		
		sqlSession.insert("category.insert", categoryVo);
		System.out.println("CategoryRepository:"+categoryVo);
	}


}
