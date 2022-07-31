package com.shopforhome.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopforhome.entity.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "category")
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="name",nullable = false,length = 25)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> productList;

}
