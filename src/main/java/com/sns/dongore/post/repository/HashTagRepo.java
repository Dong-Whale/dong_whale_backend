package com.sns.dongore.post.repository;

import com.sns.dongore.post.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepo extends JpaRepository<HashTagRepo, Long> {
    public HashTag findByName(String name);
}
