package com.sns.dongore.photo.controller;

import com.sns.dongore.photo.service.AmazonService;
import com.sns.dongore.photo.service.PhotoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/*
* TODO : 아래 컨트롤러는 태스트 용입니다. deploy 이후에는 막혀야함.
*
* */

@Controller @Slf4j
public class PhotoController {
    private PhotoService photoService;
    private AmazonService amazonService;

    @PostMapping("/api/photo")
    ResponseEntity<?> uploadPhoto(PhotoUpload photoUpload){
        ArrayList<String> photoUrls = new ArrayList<>();
        MultipartFile[] photos = photoUpload.getPhotos();
        for(int i =0; i<photos.length; i++){
            MultipartFile photo = photos[i];
            log.info("Starting upload photo {}", photo.getOriginalFilename());
            String url = amazonService.uploadFileToAWSS3(photo);
            photoUrls.add(url);
        }
        return ResponseEntity.ok().body(photoUrls);
    }
}

@Data
class PhotoUpload{
    MultipartFile[] photos;
}
