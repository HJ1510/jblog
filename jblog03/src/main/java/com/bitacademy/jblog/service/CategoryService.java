package com.bitacademy.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.CategoryRepository;
import com.bitacademy.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryVo> getCategoryList(String id) {
		return categoryRepository.findAllById(id);
	}

	public void insertCategory(CategoryVo categoryVo) {
		categoryRepository.insert(categoryVo);		
	}
}
