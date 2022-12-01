package com.example.internetshop.repo;

import com.example.internetshop.models.category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface categoryRepository extends JpaRepository<category,Long> {
    List<category> findByCategory(int category);
}
