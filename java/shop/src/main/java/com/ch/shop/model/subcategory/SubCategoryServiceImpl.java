package com.ch.shop.model.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
	@Autowired
	private SubCategoryDAO subCategoryDAO; 
	
	@Override
	public List getList(int topcategory_id) {
		// TODO Auto-generated method stub
		return subCategoryDAO.selectByTopCategoryId(topcategory_id);
	}

	@Override
	public List getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
