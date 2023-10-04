package com.example.blog.springbootblogrestapi.service;

import com.example.blog.springbootblogrestapi.payloadDtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);


    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories() ;

    CategoryDto updateCategory(CategoryDto categoryDto,Long id);

    void deleteCategory(Long id);

}
