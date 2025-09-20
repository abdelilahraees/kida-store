package com.kida.kidastore.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private String imgUrl;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Cart cart;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addressList;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> order;

    public Order addOrder(Order order) {
        this.order.add(order);
        order.setCustomer(this);
        return order;
    }

    public Address addAddress(Address address) {
        this.addressList.add(address);
        address.setCustomer(this);
        return address;
    }

    public void removeAddress(Address address) {
        this.addressList.remove(address);
    }


}
