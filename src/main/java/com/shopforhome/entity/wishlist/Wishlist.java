package com.shopforhome.entity.wishlist;

import com.shopforhome.entity.category.Category;
import com.shopforhome.entity.product.Product;
import com.shopforhome.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Data
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ElementCollection
    private List<Product> productList;

    @OneToOne
    private User user;

}
