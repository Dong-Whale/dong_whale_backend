package com.sns.dongore.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor @AllArgsConstructor @NoArgsConstructor
@Getter
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hashtag_id", nullable = false)
    Long id;

    String tagName;
    
    @OneToMany(mappedBy = "post")
    Set<HashTagRegistration> posts;
}

