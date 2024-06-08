package com.example.projectCRUD.restapi.repository;


import com.example.projectCRUD.restapi.entity.InhousePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InhousePartRepository extends JpaRepository<InhousePart, Long> {
}