package com.shopforhome.entity.discount.coupon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopforhome.entity.order.ShippingOrder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name="discount_coupon")
@Entity
@Data
public class DiscountCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="coupon_code",nullable = false,unique = true)
    private String couponCode;

    @Column(name="discount",nullable = false)
    private float discount;

    @JsonIgnore
    @OneToMany
    private List<ShippingOrder> shippingOrderList;

}
