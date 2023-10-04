package com.example.blog.springbootblogrestapi.payloadDtos;

import com.example.blog.springbootblogrestapi.entity.Post;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
public class CommentDto  {

    private long id;

    private String name;
    private String email;
    private String body;



}
