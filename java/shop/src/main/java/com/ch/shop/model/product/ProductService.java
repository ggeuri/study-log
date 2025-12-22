package com.ch.shop.model.product;

import com.ch.shop.dto.Product;

public interface ProductService {
	
	public void regist(Product product); //서비스는 트랜잭션 통으로 등록할거니까 

}
