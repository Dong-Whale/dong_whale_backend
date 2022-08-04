package com.sns.dongore.post.repository;

import com.sns.dongore.post.entity.Post;

import java.util.List;
import java.util.Set;

public interface PostRepo {
    public Post findById(Long id);
    public void save(Post post);
    public List<Post> getPosts();
}
