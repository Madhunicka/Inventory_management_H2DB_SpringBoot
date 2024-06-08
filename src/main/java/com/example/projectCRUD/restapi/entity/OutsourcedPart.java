package com.example.projectCRUD.restapi.entity;

import jakarta.persistence.Entity;

import java.util.Set;

@Entity
public class OutsourcedPart extends Parts {
    private String companyName;

    public OutsourcedPart(Long id, String name, double price, int inv, Set<Product> products, String companyName) {
        super(id, name, price, inv, products);
        this.companyName = companyName;
    }

    public OutsourcedPart(){
    //default
    }

    @Override
    public String toString() {
        return "OutsourcedPart{" +
                "companyName='" + companyName + '\'' +
                '}';
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
