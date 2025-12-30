package com.ch.shop.model.member;

import java.util.List;

import com.ch.shop.dto.Provider;

public interface ProviderDAO {
	
	public List<Provider> selectAll();
	public Provider selectByName(String provider_name);

}
