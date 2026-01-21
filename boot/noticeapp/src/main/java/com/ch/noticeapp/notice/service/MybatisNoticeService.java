package com.ch.noticeapp.notice.service;

import com.ch.noticeapp.notice.dto.mybatis.Notice;
import com.ch.noticeapp.notice.exception.NoticeErrorCode;
import com.ch.noticeapp.notice.exception.NoticeException;
import com.ch.noticeapp.notice.repository.NoticeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MybatisNoticeService {
    private final NoticeMapper noticeMapper;

    //스프링부트에서는 @Autowire보다는 생성자 주입 권고
// 서비스객체가 반드시 필요로 하는 객체에 대한 주입 강제함으로써 실수 방지
    public MybatisNoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    //글 등록
    // throw: 여기서 예외를 "발생"시킴
    // throws: 이 메서드가 예외를 "던질 수 있음"을 선언(호출자에게 넘김)
    // ✅ Checked Exception(예: SQLException)은 컴파일 때문에 throws/try-catch가 필수
    // ✅ RuntimeException(우리 NoticeException이 이 계열이면)은 throws 안 써도 됨(선택)
    // 결론: NoticeException이 RuntimeException이면 `throws NoticeException`은 문서용일 뿐 필수 아님
    public Notice regist(Notice notice) throws NoticeException {
        //insert 후 반환되는 숫자는 레코드가 반영되었는지 여부를 판단할 때 사용
        // 그 외 문법상오류, DB 문제로 인한 외부적 에러는 Exception 으로 판단
        try {
            int affected = noticeMapper.insert(notice);
            if (affected != 1) {
                throw new NoticeException(NoticeErrorCode.NOTICE_CREATE_FAIL);
            }
            return noticeMapper.findById(notice.getNoticeId()); //이 시점의 즉 insert완료된 시점의 noticeDTO에는 useGeneratedKeys 속성에 의해 pk값 채워진 상태. 어떤 글 넣었는지에대한 정보를 클라이언트에게 전송할 수 있다.
        } catch (NoticeException e) {
            //이 catch 영역은 필수는 아님. 단지 예외 관련되어 전달하는 것 외 무언가 더 하고싶을 때 이 영역을 이용
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            //여기에 올수있는 예외 종류가 너무 많고, 예측 불가하므로 Exception형으로 받아야 하며,
            // 글삭제하는데 방해되는 모든 예외는
            //모두 NoticeException으로 몰아서 처리해버리자
            throw new NoticeException(NoticeErrorCode.NOTICE_CREATE_FAIL);
        }
    }

    // 삭제 처리
    public void delete(Long noticeId) {
        try {
            int affected = noticeMapper.deleteById(noticeId);
            if (affected != 1) {
                throw new NoticeException(NoticeErrorCode.NOTICE_DELETE_FAIL);
            }
        } catch (NoticeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoticeException(NoticeErrorCode.NOTICE_DELETE_FAIL);
        }
    }

    public  Notice getContent(Long noticeId) {

        try {
            Notice notice = noticeMapper.findById(noticeId);
            if(notice ==null){
                throw new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND);
            }
            return  notice;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND);
        }
    }

    public List<Notice> getList(){

        try {
            return noticeMapper.findAll();
        } catch (Exception e) {
            throw new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND);
        }

    }

    public  Notice update(Notice notice){
        //바로 수정하기 전에 정말 그 대상 존재하는지 체크
        Notice found = noticeMapper.findById(notice.getNoticeId());
        if(found == null){
            throw new NoticeException(NoticeErrorCode.NOTICE_NOT_FOUND);
        }
        //예외 만나지 않았다면 수정 시도
        try {
            int affected = noticeMapper.update(notice);
            if(affected != 1){
                throw new NoticeException(NoticeErrorCode.NOTICE_UPDATE_FAIL);
            }
            return noticeMapper.findById(notice.getNoticeId());
        } catch (Exception e){
            e.printStackTrace();
            throw new NoticeException(NoticeErrorCode.NOTICE_UPDATE_FAIL);
        }

    }
}
