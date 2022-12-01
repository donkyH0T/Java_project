package com.example.internetshop.repo;

import com.example.internetshop.models.technique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface techniqueRepository extends JpaRepository<technique,Long> {
    List<technique> findByCategory(int category);

}
