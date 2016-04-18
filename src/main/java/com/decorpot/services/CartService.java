package com.decorpot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.decorpot.datasource.models.Cart;
import com.decorpot.datasource.repository.CartRepository;
import com.decorpot.utils.decorpotTx;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@decorpotTx
	public List<Cart> getAllCarts(){
		return cartRepository.findAll();
	}

}
