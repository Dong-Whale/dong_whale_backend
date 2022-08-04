package com.sns.dongore.post.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class HashTagRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne @JoinColumn(name = "hashtag_id")
    HashTag hashTag;

    @ManyToOne @JoinColumn(name = "post_id")
    Post post;
}
