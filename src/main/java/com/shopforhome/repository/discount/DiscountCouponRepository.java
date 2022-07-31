package com.shopforhome.repository.discount;

import com.shopforhome.entity.discount.coupon.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon,Integer> {
}
