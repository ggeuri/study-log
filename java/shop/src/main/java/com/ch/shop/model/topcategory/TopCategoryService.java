package com.ch.shop.model.topcategory;

import java.util.List;

public interface TopCategoryService {
	
	//아래의 서비스 객체조차컨트롤러가 보유할때 느슨하게 보유 
	public List getList();

}
