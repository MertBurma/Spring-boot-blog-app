package com.example.blog.springbootblogrestapi.service;

import com.example.blog.springbootblogrestapi.payloadDtos.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPost();
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id );

    void deletePost(long id);

    List<PostDto> getPostsByCategoryId(Long categoryId);






}
