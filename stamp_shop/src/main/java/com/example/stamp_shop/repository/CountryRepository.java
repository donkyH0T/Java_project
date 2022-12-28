package com.example.stamp_shop.repository;

import com.example.stamp_shop.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByName(String name);
}
