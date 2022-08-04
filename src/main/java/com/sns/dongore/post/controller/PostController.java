package com.sns.dongore.post.controller;


import com.sns.dongore.exceptions.ErrorCode;
import com.sns.dongore.exceptions.ErrorResponse;
import com.sns.dongore.post.service.PostService;
import com.sns.dongore.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/post")
@Slf4j @RequiredArgsConstructor
public class PostController {
    final private PostService postService;


    @PostMapping("/")
    ResponseEntity<?> getPosts(){
        Post post = postService.(postId);
        if(post == null){
            log.error("Post : post not found {}", postId);
            return ErrorResponse.errorResponse(ErrorCode.NOT_FOUND_POST);
        }
        return ResponseEntity.created().body(post);
    }

    @GetMapping("/{postId}")
    ResponseEntity<?> getPost(@PathVariable Long postId){
        Post post = postService.findById(postId);
        if(post == null){
            log.error("Post : post not found {}", postId);
            return ErrorResponse.errorResponse(ErrorCode.NOT_FOUND_POST);
        }
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/search")
    ResponseEntity<?> searchPostsByHashTag(@RequestParam String hashTagName){
        List<Post> posts = postService.findByHashTag(hashTagName);
        if(posts.isEmpty()){
            log.error("Post : HashTag not found {}", hashTagName);
            return ErrorResponse.errorResponse(ErrorCode.NOT_FOUND_HASHTAG);
        }
        return ResponseEntity.ok().body(posts);
    }

}
