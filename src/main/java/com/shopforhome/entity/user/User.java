package com.shopforhome.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopforhome.entity.invoice.Invoice;
import com.shopforhome.entity.order.ShippingOrder;
import com.shopforhome.enums.role.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Table(name="user")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="first_name",nullable = false,length = 25)
    private String firstName;

    @Column(name="last_name",nullable = false,length=25)
    private String lastName;

    @Column(name="email_id",unique = true,nullable = false,length = 330)
    private String emailId;

    @Column(name = "mobile_number",unique = true,nullable = false,length = 10)
    private String mobileNumber;

    @Column(name = "password",nullable = false,length = 300)
    private String password;

    @Column(name="role",nullable = false)
    private Role role;

    @ElementCollection
    private List<Integer> discountCoupons;

    @JsonIgnore
    @OneToMany
    private List<Invoice> invoiceList;

    @JsonIgnore
    @OneToMany
    private  List<ShippingOrder> shippingOrderList;
}
