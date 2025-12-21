package com.ch.shop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.SubCategory;
import com.ch.shop.model.subcategory.SubCategoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;

//하위카테고리 요청 처리하는 하위컨트롤러 
	// 목록요청처리
	// 클라가 비동기 요청 시도할 경우 서버는 절대로 html 문서를 원하지 않음 데이터보내주기 !
	@GetMapping("/subcategory/list")
	@ResponseBody // 이게 바디붙이는것 , 또한 이 어노테이션 적용하면 메시지컨버 자동적용
	public List<SubCategory> getList(int topcategory_id) {
		List subList = subCategoryService.getList(topcategory_id);
		log.debug("하위 카테고리는 " + subList);

		// 자바 객체를 JSON문자열로 개발자가 직접 바꾸지말고 JACKSON쓰자아아아아

		return subList;// 비동기요청했으니까 jsp매핑하지말고 그냥 바디에 붙여서 리턴데이터 자체를 응답정보로쓰라는것

	}

}
