package com.pt.brewit.service;

import com.pt.brewit.dto.CategoryDTO;
import com.pt.brewit.dto.SubCategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    List<SubCategoryDTO> getSubCategoriesByCategoryId(int categoryId);
}