package com.sns.dongore.post.service;

import com.sns.dongore.post.entity.Post;
import com.sns.dongore.post.repository.HashTagRegistrationRepo;
import com.sns.dongore.post.repository.HashTagRepo;
import com.sns.dongore.post.repository.PostRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)

class PostServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    PostService postService;

    @Autowired
    HashTagRegistrationRepo hashTagRegistrationRepo;

    @Autowired
    HashTagRepo hashTagRepo;

    @Test
    public void 해시태그등록하기() throws Exception{
        //given
        Post post = new Post(null, "wow", new Date(), new Date(), null);
        postService.save(post);
        //when
        postService.registerHashTag(post.getId(), "TEMP");

        //what
        int idx = postService.findByHashTag("TEMP").indexOf(post);
        Assert.assertNotEquals(idx, -1);
    }


    @Test
    void findByHashTag() {
    }
}