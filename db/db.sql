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

# 게시물 테스트 데이터 추가
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
subject = '제목1',
content = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
subject = '제목2',
content = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
subject = '제목3',
content = '내용3';

# 게시물 데이터 조회
SELECT *
FROM article;

# 게시물 데이터 초기화(데이터 추가시 1번 부터 시작)
TRUNCATE article;

# 날짜 출력 포맷 변경
SELECT A.id,
DATE_FORMAT(A.regDate, '%Y-%m-%d %H:%i:%s') AS regDate,
DATE_FORMAT(A.updateDate, '%Y-%m-%d %H:%i:%s') AS updateDate,
A.subject,
A.content
FROM article AS A;

# member 테이블 생성
CREATE TABLE `member` (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	username CHAR(100) NOT NULL UNIQUE,
	password CHAR(150) NOT NULL,
	name CHAR(100) NOT NULL
);

# 회원 테스트 데이터
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
username = 'user1',
password = '1234',
name = '양관식';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
username = 'user2',
password = '1234',
name = '오애순';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
username = 'user3',
password = '1234',
name = '양금명';

SELECT * FROM `member`;