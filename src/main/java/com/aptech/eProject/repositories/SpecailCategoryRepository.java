package com.aptech.eProject.repositories;

import com.aptech.eProject.models.Category;
import com.aptech.eProject.models.SpecialCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecailCategoryRepository  extends JpaRepository<SpecialCategory, Integer> {
    SpecialCategory findByName(String name);
}
