package com.example.blog.springbootblogrestapi.payloadDtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {



    private long id;

    @NotEmpty
    @Size(min = 2 , message = "Post Title Should be have at least 2 character")
    private String title;

    @NotEmpty
    @Size(min = 10 , message = "Post Title Should be have at least 10 character")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    private Long categoryId;
}
