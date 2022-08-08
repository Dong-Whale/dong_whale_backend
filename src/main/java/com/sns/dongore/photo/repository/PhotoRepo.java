package com.sns.dongore.photo.repository;

import java.util.List;
import java.util.Optional;

import com.sns.dongore.photo.entity.Photo;
import com.sns.dongore.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Long> {
    public Photo save(Photo Photo);
    public void deleteAll();
    public Optional<Photo> findById(Long id);
}