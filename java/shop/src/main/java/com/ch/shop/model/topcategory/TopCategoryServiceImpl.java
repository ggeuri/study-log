package com.ch.shop.model.topcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopCategoryServiceImpl implements TopCategoryService{
	@Autowired
	private TopCategoryDAO topCategoryDAO;

	@Override
	public List getList() {
		// TODO Auto-generated method stub
		return topCategoryDAO.selectAll();
	}

}
