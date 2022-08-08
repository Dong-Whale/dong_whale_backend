package com.sns.dongore.photo.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AmazonService {
    @Autowired
    private AmazonS3 amazonS3;

    @Value("${amazon.aws.bucket-name}")
    private String bucketName;

    @Autowired
    private HttpServletRequest request;

    public String generatePreSignedUrl(String fileType) {
        String randomUUID = String.valueOf(UUID.randomUUID());
        HttpMethod httpMethod = HttpMethod.PUT;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10);
        return amazonS3.generatePresignedUrl(bucketName, "public/" + randomUUID+"." + fileType, calendar.getTime(), httpMethod).toString();
    }

    public String uploadFileToAWSS3(MultipartFile photo) {
        try {
            String uploadsDir = "/uploads/";
            String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
            if(! new File(realPathtoUploads).exists())
            {
                new File(realPathtoUploads).mkdir();
            }
            log.info("realPathtoUploads = {}", realPathtoUploads);

            String orgName = photo.getOriginalFilename();
            String filePath = realPathtoUploads + orgName;
            File uploadFile = new File(filePath);
            photo.transferTo(uploadFile);

            if(!uploadFile.canRead()){
                throw new Exception("File Failed to read");
            }
            HttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(
                            RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()
                    ).build();
            HttpPut put = new HttpPut(generatePreSignedUrl(photo.getContentType()));
            HttpEntity entity = EntityBuilder.create()
                    .setFile(uploadFile)
                    .build();
            put.setEntity(entity);
            put.setHeader("Content-Type", photo.getContentType());

            httpClient.execute(put);

            String imageUrl = put.getURI().getHost() + put.getURI().getPath();
            log.info("Uploaded url is {}", imageUrl);
            return imageUrl;
        } catch (Exception e) {
            log.error("Image failed Upload {} because {}", photo.getOriginalFilename(), e.getMessage());
            return null;
        }
    }

    public void deleteFileFromAWSS3(String photoPath){

    }
}
