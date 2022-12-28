package com.example.stamp_shop.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "country")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Country {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;




    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Stamps> stamps;
}
