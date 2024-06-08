package com.example.projectCRUD.restapi.entity;

import jakarta.persistence.Entity;

import java.util.Set;

@Entity
public class InhousePart  extends Parts {
    private int partId;
    public InhousePart(){

    }


    public InhousePart(Long id, String name, double price, int inv, Set<Product> products, int partId) {
        super(id, name, price, inv, products);
        this.partId = partId;

    }



    @Override
    public String toString() {
        return "InhousePart{" +
                "partId=" + partId +
                '}';
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }


}
