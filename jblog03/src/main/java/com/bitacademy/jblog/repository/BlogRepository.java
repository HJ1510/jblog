package com.bitacademy.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BlogVo> findAll(){
		return sqlSession.selectList("blog.findAll");
	}

	public void insert(BlogVo blogVo) {
		blogVo.setProfile("profile");		
		sqlSession.insert("blog.insert", blogVo);
//		System.out.println("BlogRepository:"+blogVo);
	}

	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findByID", id);
	}

	public void updateBasic(BlogVo blogVo) {
		sqlSession.update("blog.updateBasic", blogVo);
	}



}
