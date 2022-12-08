package com.bitacademy.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void firstInsert(CategoryVo categoryVo) {		
		sqlSession.insert("category.firstInsert", categoryVo);
//		System.out.println("CategoryRepository:"+categoryVo);
	}
	
	public void insert(CategoryVo categoryVo) {		
		sqlSession.insert("category.insert", categoryVo);
		System.out.println("CategoryRepository:"+categoryVo);
	}

	public List<CategoryVo> findAllById(String id) {
		return sqlSession.selectList("category.findAllById", id);
	}



}
