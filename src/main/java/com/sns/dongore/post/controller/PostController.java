package com.sns.dongore.post.controller;


import com.sns.dongore.post.entity.Post;
import com.sns.dongore.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/post")
@Slf4j @RequiredArgsConstructor
public class PostController {
    final private PostRepository postRepo;

    @GetMapping("/{postId}")
    ResponseEntity<?> getPost(@PathVariable Long postId){
        Post post = postRepo.findById(postId);
        return ResponseEntity.ok().body(post);
    }

}
