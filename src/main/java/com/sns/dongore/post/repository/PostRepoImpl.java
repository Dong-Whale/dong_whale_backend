package com.sns.dongore.post.repository;

import com.sns.dongore.exceptions.ErrorResponse;
import com.sns.dongore.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Repository @RequiredArgsConstructor
public class PostRepoImpl implements PostRepo {
    final private EntityManager em;
    @Override
    public Post findById(Long id) {
        Post post = em.find(Post.class, id);
        return post;
    }

    public void save(Post post){
        em.persist(post);
    }


    // TODO : Pagination 위한 객체를 구현하거나 찾아서 쓰면서 이거 개선해야할듯
    // Search 기능 또한 알잘딱깔센으로 만들어야 할듯.
    public List<Post> getPosts(){
        List<Post> results = em.createQuery("select * from post").setFirstResult(0).setMaxResults(100).getResultList();
        return results;
    }
}
