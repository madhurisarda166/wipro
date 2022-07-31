package com.shopforhome.entity.order;

import com.shopforhome.entity.cart.Cart;
import com.shopforhome.entity.discount.coupon.DiscountCoupon;
import com.shopforhome.entity.product.Product;
import com.shopforhome.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Table(name="shipping_order")
@Entity
@Data
public class ShippingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    private Cart cart;

    @ManyToOne
    private User user;

    @Column(name = "date")
    private LocalDateTime orderDatetime;

    @Column(name="total_amount")
    private BigDecimal totalAmount;

    @Column(name="total_amount_after_discount")
    private BigDecimal totalAmountAfterDiscount;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @Column(name="pin_code")
    private String pinCode;

    @ManyToOne
    private DiscountCoupon discountCoupon;


}
