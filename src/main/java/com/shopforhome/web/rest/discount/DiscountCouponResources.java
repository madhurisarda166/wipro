package com.shopforhome.web.rest.discount;

import com.shopforhome.entity.discount.coupon.DiscountCoupon;
import com.shopforhome.repository.discount.DiscountCouponRepository;
import com.shopforhome.response.ActionResponse;
import com.shopforhome.service.domain.discount.DiscountCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discount-coupon")
public class DiscountCouponResources {

  @Autowired
  private DiscountCouponService discountCouponService;

  @PostMapping("/add")
  public ResponseEntity<ActionResponse> save(@RequestBody DiscountCoupon discountCoupon) {
    discountCouponService.save(discountCoupon);
    ActionResponse response=new ActionResponse();
    response.setSuccessful(true);
    response.setException(false);
    response.setResult(null);
    response.setMessage("Discount coupon added successfully");
    return ResponseEntity.ok().body(response);
  }

  @GetMapping("/get-all")
  public ResponseEntity<?> findAll(Pageable pageable) {
    Page<DiscountCoupon> discountCouponPage=discountCouponService.findAll(pageable);
    return ResponseEntity.ok().body(discountCouponPage);
  }

  @GetMapping("/get-by-id/{id}")
  public ResponseEntity<ActionResponse> findById(@PathVariable("id")int id) {
    DiscountCoupon discountCoupon=discountCouponService.findById(id);
    ActionResponse response=new ActionResponse();
    response.setSuccessful(true);
    response.setException(false);
    response.setResult(discountCoupon);
    response.setMessage(null);
    return ResponseEntity.ok().body(response);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ActionResponse> delete(@PathVariable("id")int id) {
    discountCouponService.delete(id);
    ActionResponse response=new ActionResponse();
    response.setSuccessful(true);
    response.setException(false);
    response.setResult(null);
    response.setMessage("Discount coupon deleted successfully");
    return ResponseEntity.ok().body(response);
  }


}
