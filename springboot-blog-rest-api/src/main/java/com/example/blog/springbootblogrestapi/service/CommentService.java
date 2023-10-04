package com.example.blog.springbootblogrestapi.service;

import com.example.blog.springbootblogrestapi.entity.Comment;
import com.example.blog.springbootblogrestapi.exception.BlogAPIException;
import com.example.blog.springbootblogrestapi.payloadDtos.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentByCommetId(long postId ,long commentId) throws BlogAPIException;

    CommentDto updateComment(long postId,long commentId,CommentDto commentRequest) throws BlogAPIException;


}
