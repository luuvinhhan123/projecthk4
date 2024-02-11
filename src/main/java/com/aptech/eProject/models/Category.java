package com.aptech.eProject.models;


import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Size(min = 5, max = 100, message = "Category title must be between 5 and 100 characters length")
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category",  fetch = FetchType.EAGER)
    private List<Product> products;
}