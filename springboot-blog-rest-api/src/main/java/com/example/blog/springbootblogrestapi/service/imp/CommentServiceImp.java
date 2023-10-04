package com.example.blog.springbootblogrestapi.service.imp;

import com.example.blog.springbootblogrestapi.entity.Comment;
import com.example.blog.springbootblogrestapi.entity.Post;
import com.example.blog.springbootblogrestapi.exception.BlogAPIException;
import com.example.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.example.blog.springbootblogrestapi.payloadDtos.CommentDto;
import com.example.blog.springbootblogrestapi.payloadDtos.PostDto;
import com.example.blog.springbootblogrestapi.repository.CommentRepository;
import com.example.blog.springbootblogrestapi.repository.PostRepository;
import com.example.blog.springbootblogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImp(CommentRepository commentRepository,PostRepository postRepository,ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper=mapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = maptoEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Bulunamadı",postId));


        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return maptoDto(newComment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        List<Comment>  comments = commentRepository.findByPostId(postId);


        return comments.stream().map(comment -> maptoDto(comment)).collect(Collectors.toList());




    }

    @Override
    public CommentDto getCommentByCommetId(long postId, long commentId) throws BlogAPIException {


        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Bulunamadı",postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belog to post");
        }


        return maptoDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) throws BlogAPIException {

    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" ,"Bulunamadı",postId));

    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","id",commentId));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belog to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return maptoDto(updatedComment);
    }

    //conert entity to dto
    private CommentDto maptoDto(Comment comment) {
        CommentDto commentDto = mapper.map(comment, CommentDto.class);

        return commentDto;
    }

    private Comment maptoEntity(CommentDto commentDto) {
       Comment comment = mapper.map(commentDto,Comment.class);

        return comment;
    }




}
