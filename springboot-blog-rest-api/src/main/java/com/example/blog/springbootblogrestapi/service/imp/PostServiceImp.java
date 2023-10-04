package com.example.blog.springbootblogrestapi.service.imp;

import com.example.blog.springbootblogrestapi.entity.Category;
import com.example.blog.springbootblogrestapi.entity.Post;
import com.example.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.example.blog.springbootblogrestapi.payloadDtos.PostDto;
import com.example.blog.springbootblogrestapi.repository.CategoryRepository;
import com.example.blog.springbootblogrestapi.repository.PostRepository;
import com.example.blog.springbootblogrestapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;
    private CategoryRepository categoryRepository;


    public PostServiceImp(PostRepository postRepository,ModelMapper mapper,CategoryRepository categoryRepository) {
        this.mapper=mapper;
        this.postRepository=postRepository;
        this.categoryRepository= categoryRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        //convert dto to entity

       Category category = categoryRepository.findById(postDto.getCategoryId())
                        .orElseThrow(() -> new ResourceNotFoundException("category","Id", postDto.getCategoryId()));


        Post post = maptoEntity(postDto);
        post.setCategory(category);

        Post newPost =postRepository.save(post);

        //Convert entity to dto
        PostDto postResponse = maptoDto(newPost);

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> allPosts = new ArrayList<>();

        allPosts = postRepository.findAll();

       return allPosts.stream().map(post -> maptoDto(post)).collect(Collectors.toList());


    }

    @Override
    public PostDto getPostById(long id){

       Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id" ,id));

       return maptoDto(post);






    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        //get post by id from db
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id" ,id));

        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category","id", postDto.getCategoryId()));


        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);

        return maptoDto(updatedPost);


    }

    @Override
    public void deletePost(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id" ,id));

        postRepository.delete(post);


    }

    @Override
    public List<PostDto> getPostsByCategoryId(Long categoryId) {

        List<Post> posts =postRepository.findByCategoryId(categoryId);

        return posts.stream().map((post) -> maptoDto(post)).collect(Collectors.toList());

    }

    //conert entity to dto
    private PostDto maptoDto(Post post) {

       PostDto postDto = mapper.map(post,PostDto.class);
        return postDto;
    }

    private Post maptoEntity(PostDto postDto) {

       Post post = mapper.map(postDto, Post.class);
        return post;
    }






}
