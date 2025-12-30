package com.ch.shop.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Product;
import com.ch.shop.exception.ProductException;

@Repository
public class MybatisProductDAO implements ProductDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Product product) throws ProductException{
		try {
			sqlSessionTemplate.insert("Product.insert", product);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductException("상품 insert 실패",e);
		}
		
	}

	@Override
	public List<Product> selectAll() {
		return sqlSessionTemplate.selectList("Product.selectAll");
	}

	@Override
	public List<Product> selectBySubCategoryId(int subcategory_id) {
	    return sqlSessionTemplate.selectList("Product.selectBySubCategoryId", subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("Product.select",product_id);

	}
	

}
