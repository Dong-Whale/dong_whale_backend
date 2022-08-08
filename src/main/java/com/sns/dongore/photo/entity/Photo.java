package com.sns.dongore.photo.entity;

import com.sns.dongore.post.entity.Post;
import lombok.*;

import javax.persistence.*;

@Entity @Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Photo {
    @Id @GeneratedValue @Column(name="photo_id")
    private Long id;
    private String url;

    public String toString(){
        return url;
    }
}
