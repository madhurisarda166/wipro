package com.shopforhome.service.domain.discount.impl;

import com.shopforhome.entity.discount.coupon.DiscountCoupon;
import com.shopforhome.repository.discount.DiscountCouponRepository;
import com.shopforhome.service.domain.discount.DiscountCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountCouponServiceImpl implements DiscountCouponService {

  @Autowired
  private DiscountCouponRepository discountCouponRepository;

  @Override
  public void save(DiscountCoupon discountCoupon) {
    discountCouponRepository.save(discountCoupon);
  }

  @Override
  public Page<DiscountCoupon> findAll(Pageable pageable){
    Page<DiscountCoupon> discountCoupons=discountCouponRepository.findAll(pageable);
    return discountCoupons;
  }

  @Override
  public DiscountCoupon findById(int id) {
    Optional<DiscountCoupon> discountCoupon=discountCouponRepository.findById(id);
    if(discountCoupon.isPresent()) return discountCoupon.get();
    return null;
  }

  @Override
  public void delete(int id) {
    DiscountCoupon discountCoupon=findById(id);
    if(discountCoupon!=null) discountCouponRepository.delete(discountCoupon);
  }

}
