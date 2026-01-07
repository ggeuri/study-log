package com.ch.shop.model.order;

import java.util.List;

import com.ch.shop.dto.Cart;

public interface CartService {
	public void regist(Cart cart);
	public List getList(Cart cart);
	public void update(Cart cart);
	public void remove(Cart cart);
	public void removeAll(Cart cart);

}
