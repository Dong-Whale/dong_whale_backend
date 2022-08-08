package com.sns.dongore.post.controller;


import com.sns.dongore.exceptions.ErrorCode;
import com.sns.dongore.exceptions.ErrorResponse;
import com.sns.dongore.photo.service.PhotoService;
import com.sns.dongore.post.entity.HashTag;
import com.sns.dongore.post.service.PostService;
import com.sns.dongore.post.entity.Post;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@EnableWebMvc
@Slf4j @RequiredArgsConstructor
public class PostController {
    final private PostService postService;
    final private PhotoService photoService;

    @PostMapping("/post")
    ResponseEntity<?> makePost(PostDTO postDto){
        // Post 저장 부분
        Post post = Post.builder().text(postDto.getText())
                .pubDate(new Date())
                .modifyDate(new Date())
                .photos(new ArrayList<>())
                .build();
        postService.save(post);

        // Photo 저장 부분
        for(MultipartFile photo : postDto.getPhotos()){
            String url = photoService.upload(photo);

        }

        log.info(postDto.getHashTags().toString());
        for(String hashTagName : postDto.getHashTagArray()){
            postService.registerHashTag(post.getId(), hashTagName);
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/post").toUriString());
        return ResponseEntity.created(uri).body(post);
    }

    //TODO : RequestMapping으로 하나로 합치기.
    @GetMapping("/post/all")
    ResponseEntity<?> getPosts(){
        List<Post> posts = postService.findAll();
        if(posts.isEmpty()){
            log.error("Post : post not found");
            return ErrorResponse.errorResponse(ErrorCode.NOT_FOUND_POST);
        }
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/post/{postId}")
    ResponseEntity<?> getPost(@PathVariable Long postId){
        Post post = postService.findById(postId);
        if(post == null){
            log.error("Post : post not found {}", postId);
            return ErrorResponse.errorResponse(ErrorCode.NOT_FOUND_POST);
        }
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/post/search")
    ResponseEntity<?> searchPostsByHashTag(@RequestParam String hashTag){
        List<Post> posts = postService.findByHashTag(hashTag);
        if(posts.isEmpty()){
            log.error("Post : HashTag not found {}", hashTag);
            return ErrorResponse.errorResponse(ErrorCode.NOT_FOUND_HASHTAG);
        }
        return ResponseEntity.ok().body(posts);
    }
}

@Data
class PostDTO{
    private Long userId;
    private String text;
    private String hashTags;
    private double latitude;
    private double longitude;
    private String placeName;
    private MultipartFile[] photos;

    public ArrayList<String> getHashTagArray(){
        ArrayList<String> tags = new ArrayList<>();
        if(this.hashTags.equals("") || this.hashTags == null){
            return tags;
        }
        else{
            // TODO : "#hello #hi #wow" 형태로 되어있는 스트링을 잘라서 tags ArrayList에 넣으셈.
            return tags;
        }
    }
}