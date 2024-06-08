package com.example.projectCRUD.restapi.repository;

import com.example.projectCRUD.restapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}