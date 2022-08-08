package com.sns.dongore.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hashtag_id")
    private Long id;

    @Column(unique = true)
    private String tagName;
}

