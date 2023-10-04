package com.example.blog.springbootblogrestapi.repository;

import com.example.blog.springbootblogrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
