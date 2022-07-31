package com.shopforhome.entity.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopforhome.entity.product.Product;
import com.shopforhome.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Table(name="cart")
@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="is_invoiced")
    private boolean isInvoiced;

    @ElementCollection
    private Map<Long,Long> products;

    @OneToOne
    private User user;
}
