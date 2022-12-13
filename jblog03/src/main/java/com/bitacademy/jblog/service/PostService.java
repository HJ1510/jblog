package com.bitacademy.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.jblog.repository.PostRepository;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<CategoryVo> getCategoryList(String id) {
		return postRepository.findCategoryTitle(id);
	}
	
	public void writePost(PostVo postVo) {
		postRepository.insertPost(postVo);
	}

}
