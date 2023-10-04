package com.example.blog.springbootblogrestapi.controller;

import com.example.blog.springbootblogrestapi.entity.Comment;
import com.example.blog.springbootblogrestapi.exception.BlogAPIException;
import com.example.blog.springbootblogrestapi.payloadDtos.CommentDto;
import com.example.blog.springbootblogrestapi.payloadDtos.PostDto;
import com.example.blog.springbootblogrestapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable(value = "postId") long postId)  {
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable(name = "postId") long postId ,@PathVariable(name = "commentId") long commentId) throws BlogAPIException {
        return new ResponseEntity<>(commentService.getCommentByCommetId(postId,commentId),HttpStatus.OK);

    }

    @PostMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") long postId,@PathVariable(name = "commentId") long commentId,CommentDto commentDto) throws BlogAPIException {
        CommentDto updatedComment = commentService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }


}
