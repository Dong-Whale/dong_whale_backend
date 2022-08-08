package com.sns.dongore.post.service;

import com.sns.dongore.post.entity.HashTag;
import com.sns.dongore.post.entity.HashTagRegistration;
import com.sns.dongore.post.entity.Post;
import com.sns.dongore.post.repository.HashTagRepo;
import com.sns.dongore.post.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j
public class PostService {
    private final PostRepo postRepo;
    private final HashTagRepo hashTagRepo;

    public void save(Post post){
        postRepo.save(post);
    }

    public Post findById(Long postId){
        return postRepo.findById(postId);
    }

    // TODO : 아래 함수 실행될때 마다 쿼리가 미친듯이 날아갈거 같은데 피드백을 받으면 좋을듯.
    public List<Post> findByHashTag(String hashTagName){
        log.info("Finding Posts with hashTag {}", hashTagName);
        List<Post> posts = new ArrayList<>();
        HashTag hashTag = hashTagRepo.findByName(hashTagName);
        for(HashTagRegistration htg : hashTag.getPosts()){
            posts.add(htg.getPost());
        }
        return posts;
    }

}
