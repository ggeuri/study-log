package com.ch.shop.model.order;

import java.util.Map;

import com.ch.shop.dto.Cart;

public interface RedisCartDAO {

	public void addItem(Cart cart);
	public Map<Integer, Integer> getCart(Cart cart);// Redis니까 
	public void update(Cart cart);
	public void remove(Cart cart);
	public void removeAll(Cart cart);
	
}
