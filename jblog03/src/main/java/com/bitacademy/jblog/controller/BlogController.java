package com.bitacademy.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.service.CategoryService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		Long categoeyNo = 0L;
		Long postNo = 0L;
		
		if(pathNo1.isPresent()) {
			categoeyNo = pathNo1.get();
		} else if(pathNo2.isPresent()) {
			categoeyNo = pathNo1.get();
			postNo = pathNo2.get();
		}
		model.addAttribute("list", blogService.getContentsList());
		return "blog/index";
	}
	
	@RequestMapping(value={"/admin","/admin/basic"}, method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model) { //authuser id==id인지 체크! 
		model.addAttribute("id", id);	
		System.out.println("adminBasic:"+model);
		return "blog/admin-basic"; 
	}
	
	@RequestMapping(value={"/admin","/admin/basic"}, method=RequestMethod.POST)
	public String adminBasic(@PathVariable("id") String id, BlogVo blogVo) { //authuser id==id인지 체크! 
		blogVo.setId(blogVo.getId());
		blogService.updateBlogBasic(blogVo);
		System.out.println("adminBasic2:"+blogVo);
		return "redirect:/"+id;
	}
	
	@RequestMapping(value={"/admin/category"}, method=RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, Model model){
		List<CategoryVo> categoryList = categoryService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);
		return "blog/admin-category";
	}
	
	@RequestMapping(value={"/admin/category"}, method=RequestMethod.POST)
	public String insertCategory(
			@PathVariable("id") String id,
			@RequestParam(value="title", required=true, defaultValue="") String title,
			@RequestParam(value="desc", required=true, defaultValue="") String desc, CategoryVo categoryVo){
		categoryVo.setId(id);
		categoryService.insertCategory(categoryVo);
		System.out.println("adminCategory:"+categoryVo);
		return "redirect:/"+id+"/admin/category";
	}
	
	@RequestMapping("/admin/category/delete")
	public String deleteCategory(@PathVariable("id") String id) {
		return "redirect:/"+id+"/admin/category";
	}
	
	@RequestMapping(value={"/admin/write"}, method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id){
		return "blog/admin-write";
	}
}
