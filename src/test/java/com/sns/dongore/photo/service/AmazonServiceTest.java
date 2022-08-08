package com.sns.dongore.photo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
class AmazonServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    AmazonService amazonService;

    @Test
    void uploadFileToAWSS3() throws IOException {
        String fileName = "untitled.jpg";
        String path = "src/test/java/com/sns/dongore/photo/service/untitled.jpg";
        String contentType = "jpg";

        MockMultipartFile mockMultipartFile = getMockMultipartFile(fileName, contentType, path);

        String url = amazonService.uploadFileToAWSS3(mockMultipartFile);
        Assertions.assertNotEquals(null, url);

    }
    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }
}