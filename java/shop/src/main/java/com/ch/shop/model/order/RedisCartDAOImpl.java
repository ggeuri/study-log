package com.ch.shop.model.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Cart;
import com.ch.shop.exception.CartException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class RedisCartDAOImpl implements RedisCartDAO {
	
	private static final String CART_KEY_PREFIX = "cart:";//접두어만들기 
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private String getCartKey(int member_id) {
		return CART_KEY_PREFIX+member_id;//접두어만든거이용해서 key값 더해줌. 하드코딩방지 
	}

	@Override
	public void addItem(Cart cart) throws CartException{
		if(cart.getEa()<=0) {
			throw new CartException("수량 1개이상이어야함");
		}
		
		//HSET,HGET등 명령 수행하는 객체 생성 
		//순서 key, field, value
		HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
		String key = getCartKey(cart.getMember_id());
		
		try {
			log.debug("redis에들어갈데이터는 키{} 프로덕트아이디{} 개수{}",key,cart.getProduct_id(),cart.getEa());
			Long qnt = hashOps.increment(key, Integer.toString(cart.getProduct_id()),(long)cart.getEa()); //increment는 String,String,long으로 담아야함 
			if(qnt<=0) {
				throw new CartException("장바구니수량유효하지않음");	
			}
		} catch (CartException e) {
			throw e ;
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니상품등록과정중오류발생",e);
		}
	}


	@Override
	public Map<Integer, Integer> getCart(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Cart cart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAll(Cart cart) {
		// TODO Auto-generated method stub
		
	}

}
