package com.sns.dongore.post.repository;

import com.sns.dongore.post.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTagRepo extends JpaRepository<HashTag, Long> {
    public HashTag findByTagName(String tagName);
    public HashTag save(HashTag hashTag);
}
