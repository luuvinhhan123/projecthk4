package com.aptech.eProject.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "special_category_id", referencedColumnName = "id")
    private SpecialCategory specialCategory;

    @Nullable
    private String brand;

    @NotNull
    @Size(min = 5, max = 100, message = "Product title must be between 5 and 100 characters length")
    @Column(nullable = false, unique = true)
    private String title;

    @NotNull
    @Min(value = 0, message = "Rating value must be greater than 0")
    @Max(value = 5, message = "Rating value must be less than 5")
    @Column(nullable = false)
    private Integer rating = 5;

    @NotBlank
    @Size(min = 6, max = 255, message = "Product description must between 6 and 255 characters length")
    @Column(nullable = false)
    private String description;

    @NotNull
    @Min(value = 0, message = "Price must be greater than 0")
    @Column(nullable = false)
    private double price;

    @NotNull
    @Min(value = 0, message = "Product quantity must be greater than 0")
    @Column(nullable = false)
    private Integer quantity = 0;

    @NotNull
    @Min(value = 0, message = "Product discount must be greater than 0 ")
    @Max(value = 100, message = "Product discount must be less than 100")
    @Column(nullable = false)
    private float discount;

    @Column(name = "view_number", nullable = false)
    private int viewNumber = 0;

    @Column(name = "buy_number", nullable = false)
    private int buyNumber = 0;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;
}
