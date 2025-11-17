# WebBoard - 게시판 애플리케이션

Spring Boot 3.x, MyBatis, Thymeleaf를 사용한 웹 게시판 애플리케이션입니다.

## 🎯 주요 기능

### 1. **사용자 관리**
- ✅ 회원가입 (중복 검사)
- ✅ 로그인 / 로그아웃
- ✅ 비밀번호 암호화 (BCrypt)
- ✅ 마지막 로그인 시간 기록

### 2. **게시글 관리**
- ✅ 게시글 작성 (Create)
- ✅ 게시글 목록 조회 (페이지네이션)
- ✅ 게시글 상세 조회 (조회수 증가)
- ✅ 게시글 수정 (작성자만 가능)
- ✅ 게시글 삭제 (소프트 삭제)

### 3. **댓글 관리**
- ✅ 댓글 작성
- ✅ 댓글 수정 (작성자만 가능)
- ✅ 댓글 삭제 (소프트 삭제)
- ✅ 대댓글 지원 (parent_id)

## 🛠️ 기술 스택

| 항목 | 버전 |
|------|------|
| Java | 21 |
| Spring Boot | 3.x |
| MyBatis | 3.0.3 |
| MariaDB | 11.4 |
| Thymeleaf | 기본 (Spring Boot 내장) |
| Gradle | Wrapper |

## 📋 프로젝트 구조

```
src/
├── main/
│   ├── java/com/example/webboard/
│   │   ├── WebBoardApplication.java
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   ├── PostController.java
│   │   │   ├── CommentController.java
│   │   │   └── HomeController.java
│   │   ├── domain/
│   │   │   ├── User.java
│   │   │   ├── Post.java
│   │   │   └── Comment.java
│   │   ├── dto/
│   │   │   ├── SignupRequest.java
│   │   │   ├── PostRequest.java
│   │   │   ├── PostResponse.java
│   │   │   ├── CommentRequest.java
│   │   │   └── CommentResponse.java
│   │   ├── mapper/
│   │   │   ├── UserMapper.java
│   │   │   ├── PostMapper.java
│   │   │   └── CommentMapper.java
│   │   └── service/
│   │       ├── UserService.java
│   │       ├── PostService.java
│   │       └── CommentService.java
│   └── resources/
│       ├── application.yml
│       ├── schema.sql
│       ├── mapper/
│       │   ├── UserMapper.xml
│       │   ├── PostMapper.xml
│       │   └── CommentMapper.xml
│       └── templates/
│           ├── index.html
│           ├── auth/
│           │   ├── signup.html
│           │   └── login.html
│           └── posts/
│               ├── list.html
│               ├── view.html
│               ├── create.html
│               └── edit.html
```

## 🔧 설치 및 실행

### 1. 데이터베이스 설정

MariaDB에서 스키마 생성:

```bash
mysql -u test -p!@test1234 < src/main/resources/schema.sql
```

또는 MariaDB에 직접 접속하여 `schema.sql` 파일 실행

### 2. 환경 설정

`src/main/resources/application.yml` 파일에서 DB 정보 확인:

```yaml
datasource:
  url: jdbc:mariadb://localhost:3306/board_app
  username: test
  password: !@test1234
```

### 3. 프로젝트 빌드

```bash
./gradlew build
```

### 4. 애플리케이션 실행

```bash
./gradlew bootRun
```

또는 IDE에서 `WebBoardApplication` 클래스를 실행합니다.

### 5. 애플리케이션 접속

브라우저에서 다음 URL로 접속:

```
http://localhost:8080
```

## 📖 API 및 페이지 가이드

### 인증

| 메서드 | URL | 설명 |
|--------|-----|------|
| GET | `/` | 홈 페이지 |
| GET | `/auth/signup` | 회원가입 페이지 |
| POST | `/auth/signup` | 회원가입 처리 |
| GET | `/auth/login` | 로그인 페이지 |
| POST | `/auth/login` | 로그인 처리 |
| GET | `/auth/logout` | 로그아웃 |

### 게시글

| 메서드 | URL | 설명 |
|--------|-----|------|
| GET | `/posts` | 게시글 목록 |
| GET | `/posts?page=N` | 게시글 목록 (페이지 N) |
| GET | `/posts/create` | 게시글 작성 페이지 |
| POST | `/posts` | 게시글 작성 |
| GET | `/posts/{id}` | 게시글 상세 조회 |
| GET | `/posts/{id}/edit` | 게시글 수정 페이지 |
| PUT | `/posts/{id}` | 게시글 수정 |
| DELETE | `/posts/{id}` | 게시글 삭제 |

### 댓글

| 메서드 | URL | 설명 |
|--------|-----|------|
| POST | `/posts/{postId}/comments` | 댓글 작성 |
| PUT | `/posts/{postId}/comments/{commentId}` | 댓글 수정 |
| DELETE | `/posts/{postId}/comments/{commentId}` | 댓글 삭제 |

## 🔐 보안 설정

- Spring Security 활성화
- BCrypt 비밀번호 암호화
- CSRF 보호 적용
- 인증된 사용자만 게시글/댓글 작성 가능
- 작성자만 수정/삭제 가능

## 📊 데이터베이스 스키마

### Users 테이블
- 사용자 정보 저장
- 이메일과 사용자명 UNIQUE 제약

### Posts 테이블
- 게시글 정보 저장
- 소프트 삭제 구현 (is_deleted)
- 풀텍스트 인덱스 (제목, 내용)

### Comments 테이블
- 댓글 정보 저장
- parent_id를 통한 대댓글 지원
- 소프트 삭제 구현

### Refresh Tokens 테이블
- JWT 토큰 관리 (향후 JWT 적용 시)

### Post/Comment Likes 테이블
- 좋아요 기능 (향후 구현 시)

## 🚀 향후 개선 예정

- [ ] JWT 기반 인증 (현재 Form-based)
- [ ] 좋아요 기능
- [ ] 검색 기능
- [ ] 카테고리/태그 기능
- [ ] 이미지 첨부 기능
- [ ] REST API 구현
- [ ] WebSocket을 통한 실시간 댓글
- [ ] 관리자 페이지
- [ ] 단위 테스트

## 👨‍💻 개발자

WebBoard 개발팀

## 📝 라이선스

MIT License
