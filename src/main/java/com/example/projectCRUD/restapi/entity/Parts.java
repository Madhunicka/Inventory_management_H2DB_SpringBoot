package com.example.projectCRUD.restapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Parts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;

    private double price;
    private int inv;

    @Override
    public String toString() {
        return "Parts{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", price=" + price +
                ", inv=" + inv +
                ", products=" + products +
                '}';
    }
    public Parts()
    {

    }

    public Parts(Long id, String name, double price, int inv, Set<Product> products) {
        this.id = id;
        Name = name;
        this.price = price;
        this.inv = inv;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    @ManyToMany(mappedBy = "parts")
//    @JsonBackReference
    private Set<Product> products;



}
