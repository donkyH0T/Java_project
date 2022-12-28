package com.example.stamp_shop.repository;

import com.example.stamp_shop.models.Stamps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StampsRepository extends JpaRepository<Stamps,Long> {
    List<Stamps> findByCategory(int category);

}
