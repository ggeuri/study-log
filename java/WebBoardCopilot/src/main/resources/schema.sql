-- 0) 스키마 & 기본 세팅 -------------------------------------------------------
CREATE DATABASE IF NOT EXISTS board_app
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE board_app;

-- 1) 회원(유저) --------------------------------------------------------------
CREATE TABLE users (
    id                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    email             VARCHAR(255)    NOT NULL,
    username          VARCHAR(50)     NOT NULL,
    password_hash     VARCHAR(255)    NOT NULL,     -- BCrypt/Argon2 해시
    role              ENUM('USER','ADMIN') NOT NULL DEFAULT 'USER',
    status            ENUM('ACTIVE','SUSPENDED','DELETED') NOT NULL DEFAULT 'ACTIVE',
    last_login_at     DATETIME NULL,
    created_at        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_email (email),
    UNIQUE KEY uk_users_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2) 게시글 -------------------------------------------------------------------
CREATE TABLE posts (
    id                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    author_id         BIGINT UNSIGNED NOT NULL,
    title             VARCHAR(200)    NOT NULL,
    content           LONGTEXT        NOT NULL,
    view_count        INT UNSIGNED    NOT NULL DEFAULT 0,
    is_deleted        TINYINT(1)      NOT NULL DEFAULT 0,  -- 소프트 삭제
    created_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_posts_author (author_id),
    KEY idx_posts_created_at (created_at),
    CONSTRAINT fk_posts_author
        FOREIGN KEY (author_id) REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    FULLTEXT KEY ftx_posts_title_content (title, content)  -- MariaDB InnoDB 지원
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) 댓글 (대댓글 포함: parent_id) -------------------------------------------
CREATE TABLE comments (
    id                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    post_id           BIGINT UNSIGNED NOT NULL,
    author_id         BIGINT UNSIGNED NOT NULL,
    parent_id         BIGINT UNSIGNED NULL,                -- 대댓글이면 상위 댓글
    content           TEXT             NOT NULL,
    is_deleted        TINYINT(1)       NOT NULL DEFAULT 0, -- 소프트 삭제
    created_at        DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_comments_post (post_id),
    KEY idx_comments_parent (parent_id),
    KEY idx_comments_author (author_id),
    CONSTRAINT fk_comments_post
        FOREIGN KEY (post_id) REFERENCES posts(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    CONSTRAINT fk_comments_author
        FOREIGN KEY (author_id) REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    CONSTRAINT fk_comments_parent
        FOREIGN KEY (parent_id) REFERENCES comments(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) 로그인/리프레시 토큰(선택) ----------------------------------------------
-- JWT를 쓰되 Refresh Token을 서버에 보관하고 블랙리스트/만료관리 하고 싶을 때
CREATE TABLE refresh_tokens (
    id                BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id           BIGINT UNSIGNED NOT NULL,
    token             VARCHAR(512)    NOT NULL,            -- 해시 저장 권장
    user_agent        VARCHAR(255)    NULL,
    ip_address        VARCHAR(45)     NULL,                -- IPv6까지
    expires_at        DATETIME        NOT NULL,
    revoked_at        DATETIME        NULL,
    created_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_refresh_tokens_token (token),
    KEY idx_refresh_tokens_user (user_id),
    KEY idx_refresh_tokens_expires (expires_at),
    CONSTRAINT fk_refresh_tokens_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5) 좋아요(선택) -------------------------------------------------------------
-- 게시글/댓글에 공통으로 좋아요를 걸고 싶다면 polymorphic 방식 대신 두 테이블로 단순화
CREATE TABLE post_likes (
    user_id           BIGINT UNSIGNED NOT NULL,
    post_id           BIGINT UNSIGNED NOT NULL,
    created_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, post_id),
    KEY idx_post_likes_post (post_id),
    CONSTRAINT fk_post_likes_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    CONSTRAINT fk_post_likes_post
        FOREIGN KEY (post_id) REFERENCES posts(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE comment_likes (
    user_id           BIGINT UNSIGNED NOT NULL,
    comment_id        BIGINT UNSIGNED NOT NULL,
    created_at        DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, comment_id),
    KEY idx_comment_likes_comment (comment_id),
    CONSTRAINT fk_comment_likes_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT,
    CONSTRAINT fk_comment_likes_comment
        FOREIGN KEY (comment_id) REFERENCES comments(id)
        ON DELETE CASCADE
        ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6) 간단한 인덱스/옵션 팁 ----------------------------------------------------
-- 검색 최적화: 제목/본문 FULLTEXT, 생성일 정렬 인덱스 이미 반영
-- 소프트 삭제 처리 시 WHERE is_deleted = 0 조건을 기본 전략으로 사용
