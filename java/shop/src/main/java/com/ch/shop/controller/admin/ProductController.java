package com.ch.shop.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.shop.dto.Color;
import com.ch.shop.dto.Product;
import com.ch.shop.dto.Size;
import com.ch.shop.exception.BoardException;
import com.ch.shop.exception.DirectoryException;
import com.ch.shop.exception.ProductColorException;
import com.ch.shop.exception.ProductException;
import com.ch.shop.exception.ProductImgException;
import com.ch.shop.exception.ProductSizeException;
import com.ch.shop.exception.UploadException;
import com.ch.shop.model.product.ProductService;
import com.ch.shop.model.topcategory.TopCategoryService;

import lombok.extern.slf4j.Slf4j;

/*쇼핑몰의 관리자에서 상품과 관련된 요청을 처리하는 하위 컨트롤러 */
@Controller
@Slf4j
public class ProductController {
	
	//서비스 보유(느슨하게 보유)
	@Autowired
	private TopCategoryService topCategoryService;
	@Autowired
	private ProductService productService; 
	//상품 등록폼 요청 처리 
	@GetMapping("/product/registform")
	public String getRegistForm(Model model) {
		//3단계: 상품페이지에 출력할 상위카테고리 가져오기
		//List topList=topCategoryService.getList();
		
		//4단계: 결과저장 (request직접 해야 하지만, 스프링에서는 Model객체를 이용하면 간적접으로 저장이 됨 ) 
		//jsp까지 topList를 살려서 가야하므로, 포워딩 처리해야 함.. 스프링 개발자가 redirect 를 명시하지 않으면 디폴트가 포워딩 
		//model.addAttribute("topList", topList); //request.setAttribute("topList", topList); 와 동일 
		
		return "admin/product/regist";
	}
	
	//상품 등록 요청 처리 
	/*	클라이언트가 전송한 데이터의 Content-Type이 multipart/form-data즉 텍스트 뿐만 아니라, 바이너리가 포함된 경우
	 	기존의 HttpServletRequest 객체로 바로 받지 못한다, 따라서 개발자가 스트림을 직접 제어하거나, 아니면 기존에 이미 개발되어진
	 	파일업로드 컴포넌트를 이용해야  하는데, 자바 분야에서는 apache 에서 개발한 common fileupload 라이브러리를 많이 사용한다.
	 	따라서 스프링 프레임웍도 apache commons fileupload를 내부적으로 사용한다..
	 */
	@PostMapping("/product/regist")
	@ResponseBody
	public Map<String, String> regist(Product product, int[] color, int[] size) {
		//매개변수로 지정된 객체와 , html문서의 폼에 지정된 파라미터명이 일치한다면 자동 매핑이 이루어짐
		log.debug("선택하신 하위 카테고리는 " + product.getSubCategory().getSubcategory_id());
		log.debug("상품명" + product.getProduct_name());
		log.debug("브랜드" + product.getBrand());
		log.debug("가격" + product.getPrice());
		log.debug("할인가" + product.getDiscount());

		//색상에 대한수동처리
		List colorList = new ArrayList();
		List sizeList = new ArrayList();

		for (int c : color) {
			log.debug("넘겨받은색상" + c);
			Color dto = new Color();
			dto.setColor_id(c);
			colorList.add(dto);
		}

		for (int s : size) {
			log.debug("넘겨받은사이즈" + s);
			Size dto = new Size();
			dto.setSize_id(s); // ✅ [최소수정 1] size_id 세팅 추가
			sizeList.add(dto);
		}

		product.setColorList(colorList);
		product.setSizeList(sizeList);

		log.debug("colorList는 !! " + product.getColorList());
		log.debug("sizeList는 !! " + product.getSizeList());
		log.debug("간단소개는!! " + product.getIntroduce());
		log.debug("상세설명은!! " + product.getDetail());
		
		/*
		상품등록 = 하나의 유스케이스.
		내부적으로 product/product_img/product_size/product_color 4개 저장이 묶여 실행됨.
		Controller는 "등록 요청"만 알고, 세부 작업/순서/트랜잭션 처리는 Service가 담당한다.
		*/
		try {
			productService.regist(product);
		} catch (Exception e) {
			productService.cancelUpload(product);		
			e.printStackTrace();
			throw e;
		}
		//자바객체를 json문자열로 변환해서 반환 
//		StringBuffer sb = new StringBuffer();
//		sb.append("{");
//		sb.append("\"message\" : \"상품등록\"");
//		sb.append("}");
		
		//JSON표기 자바로 표현하면 결국 Map 
		Map<String,String> body = new HashMap<String, String>();
		body.put("message","상품등록성공");
		
		
//이제 @responsebody가 알아서 바꿔줄거임 		
		return body;
	}
	
	//상품목록요청처리
	@GetMapping("/product/list") //매핑 
	public String getListPage(Model model) {
		List<Product> productList = productService.getList();
		model.addAttribute("productList",productList);
		return "admin/product/list"; //jsp 이름 
	}
	
	@GetMapping("/product/async/list")
	@ResponseBody
	public List<Product> getList(Model model) {
		List<Product> productList = productService.getList();
		return productList;
	}
	
	
	@ResponseBody
	//컨트롤러의 요청처리메서드들 중 예외 발생시 @ExceptionHandler로 예외를 처리하는 메서드가 자동으로 호출됨 
	@ExceptionHandler({BoardException.class,DirectoryException.class,ProductColorException.class,ProductException.class,ProductImgException.class,ProductSizeException.class,UploadException.class})
	public ResponseEntity<Map<String, String>> handle(Exception e) {
		log.debug("상품등록시 예외가 발생하여 handler호출");
		Map<String,String> body = new HashMap<String, String>();
		body.put("message","상품등록실패");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

}