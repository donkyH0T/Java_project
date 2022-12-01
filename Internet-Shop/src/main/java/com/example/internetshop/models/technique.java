package com.example.internetshop.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "technique")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class technique {

    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private String price;
    @Column(name = "URL")
    private String URL;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private category category;

}
