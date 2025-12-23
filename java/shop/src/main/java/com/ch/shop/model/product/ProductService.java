package com.ch.shop.model.product;

import java.util.List;

import com.ch.shop.dto.Product;

public interface ProductService {
	
	public void regist(Product product); //서비스는 트랜잭션 통으로 등록할거니까 
	public List<Product> getList();
	
	//pk값만알면지울수있으니까 Product받음 
	public void cancleUpload(Product product); 

}
