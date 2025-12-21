package com.ch.shop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Color;
import com.ch.shop.model.color.ColorService;

//관리자 - 색상관련컨트롤러 
@Controller
public class ColorController {
	@Autowired
	private ColorService colorService;
	
	@GetMapping("/color/list")
	@ResponseBody
	public List<Color> getList(){//비동기방식 색상이니까 ModelandView나 String말고 순수 데이터타입(JSON으로 응답해야함- 스프링이 @ResponseBody명시+컨버터등록으로 변환해줄거)
		return colorService.getList();
	}

}
