package com.sns.dongore.photo.entity;

import com.sns.dongore.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter
@Setter
@NoArgsConstructor @RequiredArgsConstructor
public class Photo {
    @Id @GeneratedValue @Column(name="photo_id")
    private Long id;
    private String url;

    @Column(name="post", nullable = false)
    private Post post;

}
