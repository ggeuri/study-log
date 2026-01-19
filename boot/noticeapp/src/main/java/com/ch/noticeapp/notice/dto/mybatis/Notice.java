package com.ch.noticeapp.notice.dto.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notice {
    private Long noticeId;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime regdate;

    private int hit;
}
