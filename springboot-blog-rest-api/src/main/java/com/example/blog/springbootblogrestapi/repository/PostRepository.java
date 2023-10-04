package com.example.blog.springbootblogrestapi.repository;

import com.example.blog.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

List<Post> findByCategoryId(Long categoryId);

}
