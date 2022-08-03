package com.sns.dongore.post.repository;

import com.sns.dongore.post.entity.Post;

public interface PostRepository {
    public Post findById(Long id);
}
