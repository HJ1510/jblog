package com.bitacademy.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> findCategoryTitle(String id) {
		return sqlSession.selectList("post.findCategoryTitle", id);
	}

	public void insertPost(PostVo postVo) {
		sqlSession.insert("post.insert", postVo);
	}
}
