package com.ch.shop.model.subcategory;

import java.util.List;

public interface SubCategoryService {
	//상위카테고리 소속된 목록 가져오기 
	public List getList(int topcategory_id);
	public List getList(); // 모든레코드 

}
