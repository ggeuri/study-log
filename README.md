

# 📚 Study Log

> **웹 개발 학습 및 AML 프로젝트 개발 로그 저장소**  
> 리소스 없이 코드 중심 학습을 목표로 하며, 개인 프로젝트는 별도 Repository에서 진행합니다.

---

## 📑 Table of Contents
- [🧩 About This Repository](#🧩-about-this-repository)
- [🛠️ Tech Stack](#🛠️-tech-stack)
- [🗂️ Repository Structure](#🗂️-repository-structure)
- [📈 Learning Progress](#📈-learning-progress)
- [🔍 Problem Solving Log (Template)](#🔍-problem-solving-log-template)
- [🔗 Personal Project](#🔗-personal-project)
- [🤝 Collaboration & Workflow](#🤝-collaboration--workflow)
- [📌 Notice](#📌-notice)
- [📄 License](#📄-license)

---

## 🧩 About This Repository
- 웹 개발 학습을 기록하는 저장소입니다.
- 이미지/리소스 없이 **코드 중심 학습**을 진행합니다.
- 개인 AML 프로젝트의 개발 과정도 로그 형태로 기록합니다.
- 실행 가능 여부보다 **개념 → 흐름 → 개선 방향**을 중요하게 다룹니다.

> 💡 “정답 코드”가 아니라 `왜 이렇게 이해했는가, 어떻게 개선할 것인가`를 남깁니다.

---

## 🛠️ Tech Stack

| Category | Tech |
|----------|------|
| Backend | Java, Servlet, JSP |
| Database | Oracle, MySQL |
| Data Warehouse / SQL 분석 | Redshift (대량 데이터 분석 · AML 패턴 집계) |
| Frontend | HTML, CSS, JavaScript |
| Tools | Git, GitHub, VSCode, Eclipse, DBeaver |

---

## 🗂️ Repository Structure

| Path | Description |
|------|-------------|
| `java/` | Java 문법, OOP, JDBC, CRUD, Servlet 실습 |
| `front/` | HTML/CSS/JS, Canvas, UI 렌더링 실습 |
| `java/app****/` | 날짜 기반 자바 학습 |
| `front/app****/` | 날짜 기반 프론트 학습 (리소스 없이 UI 로직 중심) |

---

## 📈 Learning Progress (학습 주제별)

| Topic | Status | Notes |
|-------|--------|-------|
| Java 기초 & 객체지향(OOP) | ✔ | 클래스/상속/인터페이스 |
| 컬렉션 · 파일 I/O · 예외 | ✔ | 실습 중심 |
| JDBC + Oracle 연동 | ✔ | PreparedStatement + Validation |
| Servlet/JSP · Web UI | ✔ | 파일 업로드·DB 연동 |
| AML Rule Engine (개인 프로젝트) | 🟨 진행 중 | 별도 Repository |
| Alert Assignment + Dashboard | 🔜 예정 | 설계 기준 구현 예정 |

---

## 🔍 Problem Solving Log


| 날짜       | 위치(폴더/파일)                                                    | 문제                                              | 원인/해결                                                                                                      |
|-----------|---------------------------------------------------------------------|---------------------------------------------------|----------------------------------------------------------------------------------------------------------------|
| 2025-11-23 | src/main/java/com/amlengine/rule/impl/IO003RapidWithdrawRule.java | IO-003 룰이 다른 uid까지 함께 카운트되어 오탐 발생 | history 전체에서 uid 구분 없이 출금 건수 집계 → match()에서 tx.uid와 다른 uid는 skip하도록 필터 추가, 샘플 CSV 3개 시나리오(2001/2002/2003)로 재검증 후 정상 동작 확인 |
| 2025-11-24 | /opt/mysql8, /lib/aarch64-linux-gnu/libaio.so.1* | MySQL 8 tar 설치 후 `mysqld --initialize` 시 libaio 관련 에러로 초기화 실패 | ARM(Ubuntu on M1/M2) 환경에서 `libaio.so.1t64`만 존재해 MySQL이 `libaio.so.1`을 찾지 못함 → `/lib`·`/usr/lib`에 `libaio.so.1t64`를 가리키는 심볼릭 링크 생성 후 `sudo ldconfig` 실행, `/opt/mysql8/data` 권한 재설정 및 `mysqld --initialize` 재실행으로 정상 초기화 확인 | 
| 2025-11-25 | Ubuntu VM `/opt/mysql8`, `/etc/profile.d/mysql8.sh` | Ubuntu 24 ARM 환경에서 MySQL 8을 tarball로 설치한 뒤, Mac에서 `mysql -h <VM IP>` 접속을 시도하면 연결이 되지 않음 | 초기 상태에서 root 계정은 `localhost`만 허용되고, 3306 포트도 방화벽(ufw)으로 막혀 있었음. → VM 내부에서 `create user 'servlet'@'%' identified by '1234';` 및 `grant all privileges on java.* to 'servlet'@'%'; flush privileges;`로 원격 접속용 계정 생성 후, `sudo ufw allow 3306/tcp`로 포트를 개방. Mac 터미널에서 `mysql -h <VM IP> -u servlet -p` 명령으로 원격 접속 테스트까지 성공. |
| 2025-11-26 | javaEE_workspace/notice (WEB-INF/lib, ROOT.war 배포 관련) | 리눅스 서버에서 /notice/list.jsp 접속 시 HTTP 500 오류 발생 | 배포한 WAR 안에 MySQL JDBC 드라이버가 포함되지 않아 `com.mysql.cj.jdbc.Driver` 클래스를 찾지 못함. 로컬의 `mysql-connector-j-8.0.33.jar`를 `src/main/webapp/WEB-INF/lib/`에 복사 후, `find + javac`로 다시 컴파일하고 `jar cvf`로 `ROOT.war`를 재생성하여 `/opt/tomcat9/webapps/`에 덮어쓰기 → Tomcat 재기동 후 정상적으로 게시판 목록 페이지 출력되는 것 확인. |
| 2025-11-27 | java/memberapp (RegistServlet, ShaManager, web.xml) | JSP 기반 회원가입 시 한글 파라미터가 깨지고 비밀번호가 평문으로만 처리되어 실제 로그인/확장에 쓰기 어려움 | RegistServlet에서 request.setCharacterEncoding("UTF-8"), response.setContentType("text/html;charset=UTF-8")로 인코딩을 통일하고, 별도 유틸 클래스 ShaManager에서 MessageDigest.getInstance("SHA-256") + 0xff & b + 1자리일 때 "0" 패딩으로 64자리 해시를 생성하는 메서드를 구현하여 회원 비밀번호를 해시로 저장·검증할 수 있는 구조로 개선함. 이후 세션(HttpSession)에 회원 DTO를 저장해 로그인 상태를 JSP에서 재사용할 수 있게 설계함. |
| 2025-11-28 | java/notice (NoticeDAO, Notice DTO, Regist/Edit/DeleteServlet, notice/*.jsp) | 공지사항 게시판 CRUD가 JSP 안에서 직접 JDBC를 호출하며 화면·쿼리·제어 로직이 섞여 있어 유지보수와 다른 UI(Swing 등)에서 재사용하기 어려움 | 게시글을 표현하는 Notice DTO와 DB 연동을 전담하는 NoticeDAO를 분리하고, RegistServlet/EditServlet/DeleteServlet은 요청 파라미터를 DTO로 매핑한 뒤 DAO 메서드만 호출하도록 단순화함. list.jsp/detail.jsp/regist.jsp는 조회 결과를 렌더링하고 폼 + JavaScript만 담당하도록 역할을 나눠, 동일 DAO/DTO를 Java SE(Swing) 클라이언트에서도 사용할 수 있는 구조로 만들고, 웹·데스크톱 양쪽에서 재사용 가능한 게시판 모듈 형태로 개선함. |
| 2025-12-01 | java/board (BoardDAO, Board DTO, PoolManager, write.jsp/list.jsp, RegistServlet, Tomcat JNDI 설정) | JSP 게시판이 각 요청마다 DriverManager로 직접 DB에 접속하고 ResultSet을 그대로 화면에 사용해 DB 접속 정보가 코드에 하드코딩되어 있고 뷰(JSP)가 JDBC 타입에 강하게 의존함 | Tomcat server.xml/context.xml/web.xml에 JNDI DataSource(커넥션 풀)을 구성하고 PoolManager 유틸로 커넥션 획득을 캡슐화한 뒤, BoardDAO.selectAll()에서 ResultSet을 List<Board> DTO 컬렉션으로 매핑하여 list.jsp는 DTO 리스트만 렌더링하도록 리팩토링함. 이를 통해 DB 설정을 서버 설정으로 분리하고 JSP가 DTO/컬렉션에만 의존하도록 개선하여, 이후 MVC 구조로 자연스럽게 확장 가능한 게시판 구조의 기반을 마련함. |
| 2025-12-02 | java/board (BoardDAO, Board DTO, Regist/Edit/DeleteServlet, board/*.jsp, PoolManager, paging) | 게시판 기능이 JDBC Connection을 매 요청마다 직접 생성·해제하고 JSP에서 ResultSet을 바로 사용해 목록·페이징을 처리하고 있어, DB 커넥션 관리가 불안정하고 화면 로직과 데이터 접근 로직이 뒤섞여 재사용과 유지보수가 어려웠음. | JNDI 기반 DataSource + PoolManager로 커넥션 획득·반납을 일원화하고, BoardDAO는 항상 Board/ List<Board> DTO만 반환하도록 수정해 JSP는 컬렉션과 페이징 산수만 다루도록 구조를 분리함. Regist/Edit/DeleteServlet은 요청 파라미터를 Board DTO에 매핑한 뒤 DAO의 insert/update/delete만 호출하게 단순화해, 커넥션풀 환경에서도 안정적으로 동작하는 재사용 가능한 게시판 모듈 형태로 개선함. |
| 2025-12-03 | javaee/ajax (Member2, PoolManager) | JSP에서 폼 전송을 동기 방식으로만 처리하다 보니 페이지가 매번 새로고침되어 UX가 떨어지고, DAO마다 커넥션 코드를 중복 작성하게 됨 | 커넥션풀을 관리하는 `PoolManager`를 싱글톤 패턴으로 구현해 모든 DAO에서 공통으로 사용하도록 하고, `Member2` DTO/DAO를 통해 회원 정보를 DB에 저장한 뒤, 동기(form submit)와 비동기(`XMLHttpRequest` + JSON 응답) 두 가지 방식으로 등록 기능을 구현하며 동기/비동기 요청 흐름과 JSON 파싱(`JSON.parse`) 과정을 정리함. |
| 2025-12-05 | java/news & map (Google Maps main.html, NewsDAO, RegistServlet, list.jsp, content.jsp, PoolManager, AJAX comment) | 공공데이터(JSON)와 Google Maps API 연동 구조를 연습하면서, 단순 JSP에 DB연동·디자인·제어 로직이 섞여 유지보수와 재사용이 어려운 기존 패턴을 개선하고자 했음. | 지도 화면은 XMLHttpRequest로 /map/list.jsp에서 JSON을 받아 JSON.parse 후 render(data)에서 위도/경도를 추출해 map.setCenter, Marker, InfoWindow로 그리도록 하고, 뉴스 게시판은 write.jsp(폼) → RegistServlet → NewsDAO → list.jsp/content.jsp 순서로 흐름을 분리했으며, PoolManager 싱글톤으로 커넥션풀 접근을 공통화하고 PagingUtil로 서버 사이드 페이징을 적용한 뒤, content.jsp에서 AJAX로 댓글 등록 뼈대를 구현해 향후 CommentDAO 기반 비동기 댓글 기능까지 확장 가능한 구조로 설계함. |
| 2025-12-08 | java/news (content.jsp, comment_regist.jsp, comment_list.jsp, CommentDAO)  | 뉴스 상세 페이지에서 댓글을 동기 폼 제출로만 처리해 새로고침이 발생하고, 자바 객체 리스트를 그대로 `out.print` 해서 JS 쪽에서 재사용하기 어렵고, FK `news_id`를 단순 int로만 다뤄 확장성이 떨어짐. | 뉴스 상세에서 `XMLHttpRequest`로 댓글 등록/목록을 비동기로 호출하고, `Comment`가 `News` 객체를 멤버로 보유하도록 설계해 `comment.getNews().getNews_id()`로 FK를 주입했다. 서버에서는 Jackson `ObjectMapper`로 `List<Comment>`를 JSON 문자열로 직렬화해 응답하고, 클라이언트에서는 `JSON.parse()`와 `printList()`로 테이블을 렌더링하여 새로고침 없이 댓글 등록/갱신이 가능한 구조로 개선했다. |
| 2025-12-08 | java/mybatisapp (pom.xml, config.xml, MybatisConfig, BoardMapper, regist) | 게시판 CRUD를 순수 JDBC로 구현하면서 매번 `Connection`, `PreparedStatement`, SQL 문자열을 DAO에 직접 작성해 중복 코드가 많고, DB 접속 정보/SQL이 소스코드에 뒤섞여 유지보수가 어려움.         | `pom.xml`에 MyBatis와 MySQL 드라이버를 의존성으로 추가하고, `config.xml`에 DB 접속 정보와 mapper 설정을 분리했다. `MybatisConfig` 싱글톤으로 `SqlSessionFactory`를 한 번만 생성해 DAO에서 `getSqlSession()`으로 세션을 받아 쓰도록 만들고, `BoardMapper.xml`에 `insert/selectAll/update/delete` SQL과 DTO 매핑을 정의해 DAO는 `sqlSession.insert("네임스페이스.id", dto)`만 호출하는 얇은 레이어로 정리해, DB 변경이나 SQL 수정 시에도 Mapper만 수정하면 되는 구조로 리팩토링했다. |
| 2025-12-10 | javaEE/mvcframework (Board MVC)     | Front Controller 기반 게시판에서 글 등록 후 새로고침 시 중복 등록 가능성(PRG 미적용), 목록→상세 이동 시 board_id 미전달, MyBatis 단건 조회 매핑 오류로 상세 페이지에 DTO가 전달되지 않음 | DispatcherServlet에 `isFoward()` 플래그를 두고 RegistController는 redirect(POST-Redirect-GET)로 변경, list.jsp에서 `board_id`를 쿼리스트링으로 전달, BoardDAO와 Mapper의 `select()`를 `Board.select` + `resultType=Board`로 수정하여 DetailController가 `Board` DTO를 받아 request에 저장하도록 구조 정리 |
| 2025-12-11 | javaEE/mvcframework (Board, Dept/Emp, MyBatis, Dispatcher) | 부서(dept2)와 사원(emp2)을 동시에 등록하는 기능에서, 한쪽 INSERT만 성공하면 데이터가 불일치하는 트랜잭션 문제가 발생할 수 있었고, MyBatis `SqlSession`을 각 DAO에서 생성하는 구조라 두 작업을 하나의 트랜잭션으로 묶기 어렵고 예외 처리 위치도 애매했음. | `RegistController`에서 `MybatisConfig`로부터 `SqlSession`을 한 번만 생성하고 이를 `DeptDAO.insert(sqlSession, dept)`와 `EmpDAO.insert(sqlSession, emp)`에 파라미터로 전달하도록 구조를 변경함. 두 DAO는 `SqlSession`을 직접 생성하지 않고 전달받은 세션에서 `insert`만 수행하며, 예외 발생 시 커스텀 예외(`DeptException`, `EmpException`)로 감싸 상위 계층에 던지도록 구현. 컨트롤러에서는 두 INSERT가 모두 성공하면 `sqlSession.commit()`을 호출하고, 하나라도 실패하면 `catch` 블록에서 `sqlSession.rollback()`을 호출해 두 테이블의 데이터 정합성을 보장함으로써, 여러 DAO를 묶는 서비스/컨트롤러 계층에서 트랜잭션 경계를 관리하는 실무적인 패턴을 연습함. |
| 2025-12-12 | javaEE/mvcframework (Dept/Emp, MyBatis, Service Layer, Transaction) | 부서(dept2)와 사원(emp2)을 동시에 등록하는 기능에서, 두 INSERT가 한 유스케이스임에도 DAO별로 SqlSession을 생성하면 트랜잭션이 분리되어 한쪽만 반영되는 데이터 불일치가 발생할 수 있었고, 예외 처리/커밋·롤백 책임이 컨트롤러에 섞이면 역할이 혼재되어 유지보수와 재사용성이 떨어졌음. | RegistController에서 DAO 호출을 제거하고 EmpService.regist(emp)로 위임하여 Controller–DAO 결합을 해소함. EmpService에서 MybatisConfig.getSqlSession()으로 세션을 1회 생성한 뒤 DeptDAO.insert(sqlSession, emp.getDept())와 EmpDAO.insert(sqlSession, emp)에 동일 세션을 전달해 하나의 트랜잭션으로 묶었고, 두 작업이 모두 성공하면 commit(), 예외 발생 시 rollback() 후 커스텀 예외(EmpException("사원등록실패", e))로 감싸 상위로 전파하도록 구현함. 마지막으로 finally에서 세션을 반납(release)해 커넥션 누수를 방지함으로써, “서비스 계층에서 트랜잭션 경계를 관리하고 컨트롤러는 요청/응답에 집중”하는 실무 패턴을 연습함. |
| 2025-12-16 | javaEE/mvcframework (Service Layer, MyBatis SqlSession, Transaction) | Dept+Emp 같은 다중 INSERT에서 DAO별로 SqlSession을 생성하면 트랜잭션이 분리되어 한쪽만 저장되는 데이터 불일치가 발생할 수 있었음. | SqlSession 생성/commit·rollback 책임을 Service로 올려 1개 세션을 DAO들에 공유해 하나의 트랜잭션으로 묶고, 예외 시 rollback + 커스텀 예외로 전파, finally에서 close/release로 누수 방지함. |

⸻

🔗 Personal Project

실무형 AML Rule Engine + Alert Dashboard 개발 중
➡️ https://github.com/ggeuri/aml-rule-engine

⸻

## 🤝 Collaboration & Workflow

- **Jira** — 티켓 생성·하위 작업·우선순위 설정·프로세스 기반 이슈 관리  
- **Confluence** — 팀 페이지 운영, 업무 프로세스·규정 문서 작성  
- **Slack** — AML/FDS 이슈 대응 커뮤니케이션 (경보 공유·피드백·대응 기록)

---

📌 Notice

- 저작권 문제로 **이미지/리소스 파일을 저장하지 않습니다.**
- UI 예제는 리소스 없이도 **코드 분석이 가능하도록 구조만 유지**합니다.
- 코드는 참고 가능하나 **상업적 사용 및 무단 재배포는 금지**합니다.

---

📄 License

MIT License © ggeuri (2025)

---
