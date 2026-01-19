package com.ch.noticeapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ch.noticeapp.notice.repository")
public class NoticeappApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeappApplication.class, args);
    }

}
