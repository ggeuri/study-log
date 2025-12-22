package com.ch.shop.model.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch.shop.dto.Product;
import com.ch.shop.dto.ProductColor;
import com.ch.shop.exception.ProductException;

import lombok.extern.slf4j.Slf4j;

/*
Service는 "업무 흐름(Use Case)"을 담당한다.
- Controller는 요청/응답, Service는 비즈니스 로직
- 여러 DAO 호출을 묶어 트랜잭션 commit/rollback을 결정한다.

예) 상품등록: product/img/size/color 중 하나라도 실패하면 전체 rollback(데이터 불일치 방지)
 */
@Service
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductDAO productDAO;
	@Autowired
	ProductColorDAO productColorDAO;

	@Override
	public void regist(Product product) throws ProductException{
		
		
		//세부업무 1 product테이블에 insert 

		log.debug("생성된 product_id = " + product.getProduct_id()); 
		productDAO.insert(product);
		log.debug("생성된 product_id = " + product.getProduct_id());  
		
		//세부업무 2 
		for(int i = 0 ; i < product.getColorList().size(); i++) {
			ProductColor productColor = new ProductColor();
			productColor.setProduct(product);
			productColor.setColor(product.getColorList().get(i));
			productColorDAO.insert(productColor);			
		}
		
		//향상된 포문으로하면 for(Color color : product.getColorList())
		
		
	}
}