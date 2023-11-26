package com.example.midterm.models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Collection;

@AllArgsConstructor
@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
    @Column(name = "enabled")
    private Boolean enabled;
}
