package com.sns.dongore.post.repository;

import com.sns.dongore.post.entity.HashTag;
import com.sns.dongore.post.entity.HashTagRegistration;
import com.sns.dongore.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HashTagRegistrationRepo extends JpaRepository<HashTagRegistration, Long> {
    Optional<HashTagRegistration> findById(Long id);
    HashTagRegistration save(HashTagRegistration hashTagRegistration);
    List<HashTag> findByPost(Post post);
    List<HashTagRegistration> findByHashTag(HashTag hashTag);
}
