package com.bitacademy.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.service.CategoryService;
import com.bitacademy.jblog.service.FileUploadService;
import com.bitacademy.jblog.service.PostService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		Long categoeyNo = 0L;
		Long postNo = 0L;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(pathNo1.isPresent()) {
			categoeyNo = pathNo1.get();
		} else if(pathNo2.isPresent()) {
			categoeyNo = pathNo1.get();
			postNo = pathNo2.get();
		}
		List<CategoryVo> categoryList = categoryService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);
	
		return "blog/index";
	}
	
	@RequestMapping(value={"/admin","/admin/basic"}, method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model) { //authuser id==id인지 체크! 
		BlogVo blogVo = blogService.findBlog(id);
		model.addAttribute("id", id);	
		model.addAttribute("title", blogVo.getTitle());
		System.out.println("adminBasic:"+blogVo);
		return "blog/admin-basic"; 
	}
	
	@RequestMapping(value={"/admin","/admin/basic"}, method=RequestMethod.POST)
	public String adminBasic(@PathVariable("id") String id, 
							@RequestParam("title") String title, 
							@RequestParam("logo-file") MultipartFile multipartFile,
							BlogVo blogVo, Model model) { //authuser id==id인지 체크!
		System.out.println(id);
		System.out.println(title);
		
		blogVo.setTitle(title);
		blogService.updateBlogBasic(blogVo);
		System.out.println("updateBlogBasic:"+blogVo);
		
		String url = fileUploadService.restore(multipartFile);
		blogVo.setProfile(url);
		
		System.out.println("fileUploadService:"+url);
//		return "redirect:/"+id;
		return "redirect:/"+id+"/admin/basic";
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
			@RequestParam("title") String title, 
			CategoryVo categoryVo, Model model){
		model.addAttribute("categoryVo", categoryVo);
		model.addAttribute("title", title);	
		System.out.println(title);
		categoryService.insertCategory(categoryVo);
		return "redirect:/"+id+"/admin/category";
	}
	 
	@RequestMapping("/admin/category/delete/{no}")
	public String deleteCategory(@PathVariable("id") String id, @PathVariable("no") Long no) {
		categoryService.deleteCategory(no);	
		return "redirect:/"+id+"/admin/category";
	}
	
	@RequestMapping(value={"/admin/write"}, method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model){
		List<CategoryVo> categoryList = postService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);
		return "blog/admin-write";
	}
	
	@RequestMapping(value={"/admin/write"}, method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo postVo, Model model) {
		model.addAttribute("postVo", postVo);
		postService.writePost(postVo);
		System.out.println(postVo);
		return "redirect:/"+id;
	}
}