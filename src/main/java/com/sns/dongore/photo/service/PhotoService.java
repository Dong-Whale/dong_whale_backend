package com.sns.dongore.photo.service;

import com.sns.dongore.photo.entity.DTO.PhotoDTO;
import com.sns.dongore.photo.entity.Photo;
import org.springframework.web.multipart.MultipartFile;

public class PhotoService {
    public String upload(MultipartFile photo){
        //TODO : Heroku에서 돌아가고있는 파일 서버에 올리고 url 반환.
        return "This is photo url";
    }

    public Photo save(PhotoDTO photoDTO){
        Photo photo = new Photo();
        return photo;
    }
}
