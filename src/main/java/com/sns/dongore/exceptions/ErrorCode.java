package com.sns.dongore.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter @RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "Post가 존재하지 않습니다."),
    NOT_FOUND_HASHTAG(HttpStatus.NOT_FOUND, "HashTag가 존재하지 않습니다.");


    private final HttpStatus statusCode;
    private final String detail;


}
