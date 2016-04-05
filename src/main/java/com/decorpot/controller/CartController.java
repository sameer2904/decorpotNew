package com.decorpot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.decorpot.datasource.models.Cart;
import com.decorpot.services.CartService;

@RestController
@RequestMapping(value = "/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cart> getAllCarts(){
		return cartService.getAllCarts();
	}

}
