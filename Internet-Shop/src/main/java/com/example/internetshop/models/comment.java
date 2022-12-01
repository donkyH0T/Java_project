package com.example.internetshop.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "text")
    private String text;

    public comment(String name, String text) {
        this.name = name;
        this.text = text;
    }
}
