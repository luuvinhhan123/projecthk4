package com.aptech.eProject.dto;

import java.util.List;

import com.aptech.eProject.models.Category;
import com.aptech.eProject.models.SpecialCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private List<Category> cate;
    private List<SpecialCategory> speccailcate;
}
