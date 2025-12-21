package com.ch.shop.model.size;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl implements SizeService {
	@Autowired
	SizeDAO sizeDAO;

	@Override
	public List getList() {
		// TODO Auto-generated method stub
		return sizeDAO.selectAll();
	}

}
