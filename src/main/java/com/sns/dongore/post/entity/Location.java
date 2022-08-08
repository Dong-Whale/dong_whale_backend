package com.sns.dongore.post.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private double latitude;
    private double longitude;

    private String placeName;

}
