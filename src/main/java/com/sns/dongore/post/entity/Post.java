package com.sns.dongore.post.entity;

import com.sns.dongore.photo.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Getter
@Builder
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long id;
    private String text;

    private Date pubDate;
    private Date modifyDate;

    protected Post(){
        text = "";
        pubDate = null;
        modifyDate = null;
    }


    @OneToMany(mappedBy = "post")
    private List<Photo> photos;
}
