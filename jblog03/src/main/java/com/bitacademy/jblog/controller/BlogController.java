package com.bitacademy.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bitacademy.jblog.service.BlogService;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2) {
		Long categoeyNo = 0L;
		Long postNo = 0L;
		
		if(pathNo1.isPresent()) {
			categoeyNo = pathNo1.get();
		} else if(pathNo2.isPresent()) {
			categoeyNo = pathNo1.get();
			postNo = pathNo2.get();
		}
		return "blog/index";
	}
	
	@RequestMapping({"/admin", "/admin/basic"})
	public String adminBasic(@PathVariable("id") String id) { //authuser id==id인지 체크! 
		return "blog/admin-basic";
	}
}
