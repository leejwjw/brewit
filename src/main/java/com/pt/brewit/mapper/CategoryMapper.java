package com.pt.brewit.mapper;

import com.pt.brewit.dto.CategoryDTO;
import com.pt.brewit.dto.SubCategoryDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDTO> getAllCategories();
    List<SubCategoryDTO> getSubCategoriesByCategoryId(int categoryId);
}