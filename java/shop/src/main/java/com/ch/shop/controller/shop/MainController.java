package com.ch.shop.controller.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch.shop.model.topcategory.TopCategoryService;

@Controller
public class MainController {
	
	//쇼핑몰이건,관리자모드건 MVC로 개발되었다면 모델은 재사용 가능
	@Autowired
	private TopCategoryService topCategoryService; 
	
	@GetMapping("/")
	public ModelAndView getMain() {
		//여러데이터베이스 연동 중 상위카테고리 가져오기 
		List topList = topCategoryService.getList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList",topList);
		mav.setViewName("shop/index");
		
		return mav; 
	}

}
