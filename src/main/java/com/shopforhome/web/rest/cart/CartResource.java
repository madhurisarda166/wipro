package com.shopforhome.web.rest.cart;

import com.shopforhome.entity.cart.Cart;
import com.shopforhome.response.ActionResponse;
import com.shopforhome.service.domain.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartResource {

  @Autowired
  private CartService cartService;

  @PostMapping("/add")
  public ResponseEntity<ActionResponse> save(Cart cart) {
    ActionResponse response=new ActionResponse();
    long id=cartService.save(cart);
    response.setSuccessful(true);
    response.setException(false);
    response.setResult(id);
    response.setMessage(null);
    return ResponseEntity.ok().body(response);
  }

}
