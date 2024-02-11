package com.aptech.eProject.repositories;

import com.aptech.eProject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface CategoryRepository extends JpaRepository<Category, Integer> {
     Category findByName(String name);
}
