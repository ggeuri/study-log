package com.ch.shop.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.ProductColor;
import com.ch.shop.exception.ProductColorException;

@Repository
public class MybatisProductColorDAO implements ProductColorDAO{
	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;

	@Override
	public void insert(ProductColor productColor)throws ProductColorException{
		try {
			sqlsessionTemplate.insert("ProductColor.insert",productColor);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductColorException("상품색상 등록실패",e);
		}
	}

}
