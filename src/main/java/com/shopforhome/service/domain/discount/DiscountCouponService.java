package com.shopforhome.service.domain.discount;

import com.shopforhome.entity.discount.coupon.DiscountCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DiscountCouponService {

  void save(DiscountCoupon discountCoupon);
  Page<DiscountCoupon> findAll(Pageable pageable);
  DiscountCoupon findById(int id);
  void delete(int id);

}
