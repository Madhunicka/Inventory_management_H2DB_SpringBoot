package com.example.projectCRUD.restapi.repository;

import com.example.projectCRUD.restapi.entity.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartsRepository extends JpaRepository<Parts, Long> {
    // You can add custom query methods here if needed
}