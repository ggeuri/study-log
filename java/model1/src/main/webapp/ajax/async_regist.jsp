<%@page import="java.util.List"%>
<%@page import="java.util.function.DoubleToIntFunction"%> <%-- (현재 예제에서는 사용하지 않음) --%>
<%@page import="com.ch.model1.dto.Member2"%>
<%@page import="com.ch.model1.board.repository.Member2DAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%! 
    // DAO는 JSP가 서블릿 클래스로 변환될 때 멤버변수로 한 번만 생성됨.
    // → 매 요청마다 new 하지 않고, 같은 Member2DAO 인스턴스를 재사용.
    Member2DAO dao = new Member2DAO(); 
%>

<%
    // ----------------------------------------------------------------------
    // 1. 클라이언트에서 넘어온 파라미터 인코딩 설정
    // ----------------------------------------------------------------------
    // 톰캣 로그에 출력하면, 우리 환경에선 이클립스 콘솔에서 확인 가능.
    // 한글 파라미터가 깨지지 않도록 먼저 요청 인코딩을 UTF-8로 지정.
    request.setCharacterEncoding("utf-8");  // 파라미터 깨지지 않도록 인코딩 지정 

    // ----------------------------------------------------------------------
    // 2. 파라미터 받기 (id, name, email)
    // ----------------------------------------------------------------------
    String id    = request.getParameter("id");
    String name  = request.getParameter("name");
    String email = request.getParameter("email");

    System.out.println(id + name + email);  // 디버깅용으로 콘솔에 출력

    // ----------------------------------------------------------------------
    // 3. DTO에 모으기 (파라미터 → Member2 객체로 매핑)
    // ----------------------------------------------------------------------
    Member2 dto = new Member2();
    dto.setId(id);
    dto.setName(name);
    dto.setEmail(email);

    // ----------------------------------------------------------------------
    // 4. DAO를 이용해 DB에 insert 수행
    // ----------------------------------------------------------------------
    int result = dao.insert(dto);
    System.out.println(result);  // insert 결과(성공 여부)를 로그로 확인

    // ----------------------------------------------------------------------
    // 5. 비동기 요청(AJAX)에 대한 응답 처리 개념 정리
    // ----------------------------------------------------------------------
    // 아래와 같이 비동기 요청에 대해 응답정보로 "페이지 접속"을 일으키는 코드
    // (예: sendRedirect, location.href 등)를 작성하면
    //
    //  → 클라이언트(브라우저)가 지정한 URL로 "재접속 시도"
    //  → 해당 HTML을 새로 요청해서 화면에 렌더링
    //  → 결과적으로 "새로고침 효과"가 발생
    //
    // 이렇게 되면 원래 AJAX의 목적이었던
    //   "새로고침 없는 화면 갱신(without reloading)"
    // 기능이 사라져버림.
    //
    // 해결책:
    //  - 화면 전체(HTML)를 다시 보내지 말고
    //  - 순수한 목록 데이터(JSON, XML 등)만 전송
    //  - 클라이언트에서 JS로 그 데이터를 받아서 DOM을 동적으로 갱신
    //
    // 즉, 비동기 요청에 대한 응답은 "데이터만 보내고 화면 갱신은 JS에 맡긴다"가 핵심.
    //
    // 아래 코드는 그런 의미에서 AJAX에 맞지 않는 예시라서 주석 처리:
    // response.sendRedirect("/ajax/async_regist.jsp");

    // ----------------------------------------------------------------------
    // 6. 게시물(회원) 목록 가져오기 + JSON 문자열 생성 예시 (AJAX 핵심 원리)
    // ----------------------------------------------------------------------
    // 이 JSP에서 insert만 처리하고 끝낼 수도 있지만,
    // "등록 후 최신 목록을 다시 내려보내고 싶다"면
    // 아래와 같이 selectAll() + JSON 문자열 생성 로직을 사용할 수 있음.
    // 지금은 설명용/예시라 전체를 주석으로 묶어둔 상태.
    // ----------------------------------------------------------------------
    /*
    // 게시물(회원) 목록 가져오기
    List<Member2> list = dao.selectAll();

    // JSON은 문자열이지만, 형식이 JS 객체 리터럴과 거의 동일하기 때문에
    // 자바스크립트에서 JSON.parse()로 쉽게 객체/배열로 변환 가능.
    StringBuffer data = new StringBuffer(); // 문자열임, 객체 아님 

    data.append("[");  // JSON 배열 시작
    for (int i = 0; i < list.size(); i++) {
        Member2 m = list.get(i);

        data.append("{")
            .append("\"member2_id\":").append(m.getMember2_id()).append(",")
            .append("\"id\":\"").append(m.getId()).append("\",")
            .append("\"name\":\"").append(m.getName()).append("\",")
            .append("\"email\":\"").append(m.getEmail()).append("\"")
            .append("}");

        if (i < list.size() - 1) {
            data.append(",");  // 요소 사이에 콤마 추가
        }
    }
    data.append("]");  // 배열 끝

    // 클라이언트로 JSON 문자열 전송
    out.print(data.toString()); 
    */

    // ----------------------------------------------------------------------
    // 7. 동기 요청 vs 비동기 요청 정리
    // ----------------------------------------------------------------------
    // 만일 요청 유형이 "동기 방식"이었다면(즉, 일반 form submit 등),
    // 유저는 최종적으로 "목록 화면"을 보아야 하므로
    //   out.print("location.href='list.jsp';")
    // 또는
    //   response.sendRedirect("list.jsp");
    // 같은 코드를 작성하게 됨 → 페이지 이동(새로고침) 발생.
    //
    // 따라서 "비동기 요청(AJAX)"에서는 절대 "문서(HTML) 또는 링크"를 보내면 안 되고,
    // 순수한 데이터만 보내야 한다.
    //
    // 보내야 할 데이터 유형:
    //   과거에는 XML도 많이 사용했지만,
    //   현재는 JSON(JavaScript Object Notation)이 사실상 대세.
    //   → 가볍고, JS와 궁합이 좋아서 프론트엔드에서 쓰기 편함.
%>