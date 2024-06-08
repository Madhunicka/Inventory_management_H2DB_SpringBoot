package com.example.projectCRUD.restapi.repository;

import com.example.projectCRUD.restapi.entity.OutsourcedPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutsourcedPartRepository extends JpaRepository<OutsourcedPart, Long> {
}