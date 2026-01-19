package com.ch.noticeapp.notice.repository;

import com.ch.noticeapp.notice.dto.mybatis.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// Mapper인터페이스 사용시 굳이 DAO별도로 만들어서 SqlSessionTemplate사용할 필요없음
@Mapper
public interface NoticeMapper {
    int insert(Notice notice);
    List<Notice> findAll();
    Notice findById(Long noticeId);
    int update(Notice notice);
    int deleteById(Long noticeId);
    int increaseHit(Long noticeId);
}
