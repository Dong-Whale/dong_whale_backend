package com.sns.dongore.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity @Getter @AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false)
    private Long id;
    private String title;
    private String text;

    @ElementCollection
    private List<String> photos;

    @OneToMany(mappedBy = "hashTag")
    Set<HashTagRegistration> hashTags;

}
