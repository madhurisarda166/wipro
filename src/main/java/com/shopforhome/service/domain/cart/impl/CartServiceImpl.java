package com.shopforhome.service.domain.cart.impl;

import com.shopforhome.entity.cart.Cart;
import com.shopforhome.repository.cart.CartRepository;
import com.shopforhome.service.domain.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

  @Autowired
  private CartRepository cartRepository;

  @Override
  public long save(Cart cart)  {
    Cart cart1=cartRepository.save(cart);
    return cart1.getId();
  }

}
