package com.pt.brewit.service.impl;

import com.pt.brewit.dto.CategoryDTO;
import com.pt.brewit.dto.SubCategoryDTO;
import com.pt.brewit.mapper.CategoryMapper;
import com.pt.brewit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryMapper.getAllCategories();
    }

    @Override
    public List<SubCategoryDTO> getSubCategoriesByCategoryId(int categoryId) {
        return categoryMapper.getSubCategoriesByCategoryId(categoryId);
    }
}
