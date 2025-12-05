

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
