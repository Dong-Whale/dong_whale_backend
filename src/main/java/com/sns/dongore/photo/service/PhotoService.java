package com.sns.dongore.photo.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.UploadObjectRequest;
import com.sns.dongore.photo.entity.DTO.PhotoDTO;
import com.sns.dongore.photo.entity.Photo;
import com.sns.dongore.photo.repository.PhotoRepo;
import com.sns.dongore.post.entity.Post;
import com.sns.dongore.post.repository.PostRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service @Slf4j @Transactional
public class PhotoService {

    @Autowired
    private AmazonService amazonService;

    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    private PostRepo postRepo;

    public void uploadAll(Post post, MultipartFile[] photos) {
        for(MultipartFile photoFile : photos){
            String photoUrl = amazonService.uploadFileToAWSS3(photoFile);
            if(photoUrl == null){
                log.error("Error while upload photo {}", photoFile.getOriginalFilename());
                break;
            }
            Photo photo = new Photo(null, photoUrl);
            photoRepo.save(photo);
            post.getPhotos().add(photo);
        }
        postRepo.save(post);
    }

    public Photo save(Photo photo){
        photoRepo.save(photo);
        return photo;
    }

    public void deletePhoto(Photo photo){
        //TODO : s3에서 삭제하는 코드 작성하기.

    }

}
