# 데이터 베이스 삭제
DROP DATABASE IF EXISTS text_board;

# 데이터 베이스 생성
CREATE DATABASE text_board;

# 데이터 베이스 선택
USE text_board;

# article 테이블 생성
CREATE TABLE article (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	subject CHAR(100) NOT NULL,
	content TEXT NOT NULL
);

# 게시물 데이터 추가
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
subject = '제목',
content = '내용';

# 게시물 데이터 조회
SELECT *
FROM article;

# 게시물 데이터 초기화(데이터 추가시 1번 부터 시작)
TRUNCATE article;