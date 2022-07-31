package com.shopforhome.entity.product;

import com.shopforhome.entity.category.Category;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table(name="product")
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name",nullable = false,length = 100)
    private String name;

    @Column(name = "description",length = 255)
    private String description;

    @ElementCollection
    private List<String> searchKeywordList;

    @ManyToOne
    private Category category;

    @Column(name="price",nullable = false)
    private BigDecimal price;

    @Column(name = "image1",nullable = true)
    private String image1;

    @Column(name = "image2",nullable =true)
    private String image2;

    @Column(name = "image3",nullable = true)
    private String image3;

    @Column(name="gst",nullable = false)
    private float gst;

    @Column(name="qty",nullable = false)
    private int qty;
}
