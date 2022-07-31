package com.shopforhome.entity.invoice;

import com.shopforhome.entity.order.ShippingOrder;
import com.shopforhome.entity.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="invoice")
@Entity
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="invoice_number")
    private String invoiceNumber;

    @Column(name="invoice_datetime")
    private LocalDateTime invoiceDatetime;

    @OneToOne
    private ShippingOrder shippingOrder;

    @ManyToOne
    private User user;
}
