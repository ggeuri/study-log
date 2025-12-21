package com.ch.shop.model.subcategory;

import java.util.List;

public interface SubCategoryDAO {
	public List selectByTopCategoryId(int topcategory_id);

}
