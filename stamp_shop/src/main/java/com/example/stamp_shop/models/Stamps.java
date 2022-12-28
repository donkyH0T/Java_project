package com.example.stamp_shop.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "stamps")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Stamps {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private String price;
    @Column(name = "URL")
    private String URL;
    @Column(name = "category")
    private int category;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    Country country;
}
