package com.example.internetshop.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class category {

    @Id
   private Long id;
    @Column(name = "category")
   private int category;

   @OneToMany(cascade = CascadeType.ALL)
   private List<technique> techniques =new ArrayList<>();
}
