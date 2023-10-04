package com.example.blog.springbootblogrestapi.service.imp;

import com.example.blog.springbootblogrestapi.entity.Category;
import com.example.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.example.blog.springbootblogrestapi.payloadDtos.CategoryDto;
import com.example.blog.springbootblogrestapi.repository.CategoryRepository;
import com.example.blog.springbootblogrestapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImp(CategoryRepository categoryRepository,ModelMapper modelMapper) {
        this.categoryRepository=categoryRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = modelMapper.map(categoryDto, Category.class);

        Category savedCategory= categoryRepository.save(category);

      return   modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {

        Category getCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));




        return modelMapper.map(getCategory,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();



        return categories.stream().map((category) -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category" ,"id",id ));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setId(id);

        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","id" ,id));

        if(category != null) {
            categoryRepository.deleteById(id);
        }

    }

}
