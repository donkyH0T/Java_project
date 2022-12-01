package com.example.internetshop.repo;

import com.example.internetshop.models.comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepository extends JpaRepository<comment,Long> {
}
