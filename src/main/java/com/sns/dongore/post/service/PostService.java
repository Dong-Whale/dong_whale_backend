package com.sns.dongore.post.service;

import com.sns.dongore.post.entity.HashTag;
import com.sns.dongore.post.entity.HashTagRegistration;
import com.sns.dongore.post.entity.Post;
import com.sns.dongore.post.repository.HashTagRegistrationRepo;
import com.sns.dongore.post.repository.HashTagRepo;
import com.sns.dongore.post.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class PostService {
    private final PostRepo postRepo;
    private final HashTagRepo hashTagRepo;
    private final HashTagRegistrationRepo hashTagRegistrationRepo;


    public void save(Post post){
        postRepo.save(post);
    }

    public Post findById(Long postId){
        return postRepo.findById(postId);
    }

    public List<Post> findAll(){
        return postRepo.getPosts();
    }

    public void registerHashTag(Long postId, String hashTagName){
        Post post = postRepo.findById(postId);
        HashTag hashTag = hashTagRepo.findByTagName(hashTagName);
        if(hashTag == null){
            hashTag = new HashTag(null, hashTagName);
            hashTagRepo.save(hashTag);
        }
        HashTagRegistration hashTagRegistration = new HashTagRegistration(null,  post,hashTag);
        hashTagRegistrationRepo.save(hashTagRegistration);
    }

    // TODO : 아래 함수 실행될때 마다 쿼리가 미친듯이 날아갈거 같은데 피드백을 받으면 좋을듯.
    public List<Post> findByHashTag(String hashTagName){
        log.info("Finding Posts with hashTag {}", hashTagName);
        HashTag hashTag = hashTagRepo.findByTagName(hashTagName);
        List<HashTagRegistration> registrations = hashTagRegistrationRepo.findByHashTag(hashTag);
        List<Post> posts = new ArrayList<>();
        for(HashTagRegistration registration : registrations){
            posts.add(registration.getPost());
        }
        return posts;
    }

}
