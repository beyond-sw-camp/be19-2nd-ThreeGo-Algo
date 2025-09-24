DROP DATABASE IF EXISTS algo;
CREATE DATABASE algo CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE algo;

-- 1. 자식 테이블부터 DROP
DROP TABLE IF EXISTS `Likes`;
DROP TABLE IF EXISTS `Member_Algo_Correct_Quiz_History`; 
DROP TABLE IF EXISTS `Algo_Post_Image`;
DROP TABLE IF EXISTS `Algo_Quiz_Option`;
DROP TABLE IF EXISTS `Algo_Comment`;
DROP TABLE IF EXISTS `Algo_Member_Progress`;
DROP TABLE IF EXISTS `Coding_Post_Image`;
DROP TABLE IF EXISTS `Coding_Comment`;
DROP TABLE IF EXISTS `Career_Info_Comment`;
DROP TABLE IF EXISTS `Study_Post_Image`;
DROP TABLE IF EXISTS `Study_Comment`;
DROP TABLE IF EXISTS `Study_Recruit_Comment`;
DROP TABLE IF EXISTS `Study_Recruit_Member`;
DROP TABLE IF EXISTS `Study_Member`;
DROP TABLE IF EXISTS `Study_Milestone`;

-- 2. 중간 부모 테이블 DROP
DROP TABLE IF EXISTS `Algo_Post`;
DROP TABLE IF EXISTS `Algo_Quiz_Question`;
DROP TABLE IF EXISTS `Coding_Post`;
DROP TABLE IF EXISTS `Career_Info_Post`;
DROP TABLE IF EXISTS `Study_Post`;
DROP TABLE IF EXISTS `Study_Roadmap`;
DROP TABLE IF EXISTS `Study`;
DROP TABLE IF EXISTS `Study_Recruit_Post`;

-- 3. 최상위 부모 테이블 DROP
DROP TABLE IF EXISTS `Algo_Roadmap`;
DROP TABLE IF EXISTS `Coding_Problem`;
DROP TABLE IF EXISTS `Report`;
DROP TABLE IF EXISTS `Report_Category`;
DROP TABLE IF EXISTS `Report_Type`;
DROP TABLE IF EXISTS `Member_Password_History`;
DROP TABLE IF EXISTS `Member_Login_History`;
DROP TABLE IF EXISTS `Member_Login_Failed_History`;
DROP TABLE IF EXISTS `Member_Attendance_History`;
DROP TABLE IF EXISTS `Member_Role`;
DROP TABLE IF EXISTS `Member`;
DROP TABLE IF EXISTS `Member_Rank`;
DROP TABLE IF EXISTS `Role`;

-- 회원 등급
CREATE TABLE `Member_Rank` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` ENUM('코알못','코뉴비','코좀알','코잘알','코신') NOT NULL,
  `min_point` INT NOT NULL,
  `image_url` VARCHAR(255) NOT NULL
);

-- 권한
CREATE TABLE `Role` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` ENUM('MEMBER','ADMIN') NOT NULL DEFAULT 'MEMBER',
  `description` VARCHAR(255) NOT NULL
);

-- 회원
CREATE TABLE `Member` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `rank_id` INT NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(100) NOT NULL UNIQUE,
  `point` INT NOT NULL DEFAULT 0,
  `reported_count` INT NOT NULL DEFAULT 0,
  `status` ENUM('ACTIVE','INACTIVE','BLOCKED') NOT NULL DEFAULT 'ACTIVE',
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_Rank_TO_Member` FOREIGN KEY (`rank_id`) REFERENCES `Member_Rank`(`id`)
);

-- 회원별 권한 매핑
CREATE TABLE `Member_Role` (
  `role_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`role_id`, `member_id`),
  CONSTRAINT `FK_Role_TO_Member_Role` FOREIGN KEY (`role_id`) REFERENCES `Role`(`id`),
  CONSTRAINT `FK_Member_TO_Member_Role` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 비밀번호 변경 이력
CREATE TABLE `Member_Password_History` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `before_pwd` VARCHAR(255) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  CONSTRAINT `FK_Member_TO_Member_Password_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 로그인 기록
CREATE TABLE `Member_Login_History` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `ip_address` VARCHAR(50) NULL,
  CONSTRAINT `FK_Member_TO_Member_Login_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 로그인 실패 기록
CREATE TABLE `Member_Login_Failed_History` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `ip_address` VARCHAR(50) NULL,
  `reason` VARCHAR(255) NULL,
  CONSTRAINT `FK_Member_TO_Login_Failed_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 출석 기록
CREATE TABLE `Member_Attendance_History` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `attend_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_TO_Attendance_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 알고리즘 로드맵
CREATE TABLE `Algo_Roadmap` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(500) NULL,
  `order` INT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `question_count` INT NOT NULL DEFAULT 0
);

-- 알고리즘 학습 게시물
CREATE TABLE `Algo_Post` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `roadmap_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `comment_count` INT NOT NULL DEFAULT 0,
  `like_count` INT NOT NULL DEFAULT 0,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Member_TO_Algo_Post` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Algo_Roadmap_TO_Algo_Post` FOREIGN KEY (`roadmap_id`) REFERENCES `Algo_Roadmap`(`id`)
);

-- 알고리즘 학습 게시물 이미지
CREATE TABLE `Algo_Post_Image` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Algo_Post_TO_Algo_Post_Image` FOREIGN KEY (`post_id`) REFERENCES `Algo_Post`(`id`)
);

-- 개념 확인 문제
CREATE TABLE `Algo_Quiz_Question` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `algo_post_id` INT NOT NULL,
  `question` VARCHAR(500) NOT NULL,
  `type` ENUM('MULTIPLE','OX') NOT NULL,
  CONSTRAINT `FK_Algo_Post_TO_Algo_Quiz_Question` FOREIGN KEY (`algo_post_id`) REFERENCES `Algo_Post`(`id`)
);

-- 개념 확인 문제 선택지
CREATE TABLE `Algo_Quiz_Option` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `question_id` INT NOT NULL,
  `option_text` VARCHAR(255) NOT NULL,
  `is_correct` TINYINT(1) NOT NULL,
  CONSTRAINT `FK_Algo_Quiz_Question_TO_Algo_Quiz_Option` FOREIGN KEY (`question_id`) REFERENCES `Algo_Quiz_Question`(`id`)
);

-- 회원별 퀴즈 맞춘 이력
CREATE TABLE `Member_Algo_Correct_Quiz_History` (
	`member_id` INT NOT NULL,
	`algo_quiz_question_id` INT NOT NULL,
	PRIMARY KEY (`member_id`, `algo_quiz_question_id`),
	CONSTRAINT `FK_Member_TO_Member_Algo_Correct_Quiz_History` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
	CONSTRAINT `FK_Algo_Quiz_TO_Member_Algo_Correct_Quiz_History` FOREIGN KEY (`algo_quiz_question_id`) REFERENCES `Algo_Quiz_Question`(`id`)
);

-- 알고리즘 학습 게시물 댓글
CREATE TABLE `Algo_Comment` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `parent_id` INT NULL,
  `member_id` INT NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Algo_Post_TO_Algo_Comment` FOREIGN KEY (`post_id`) REFERENCES `Algo_Post`(`id`),
  CONSTRAINT `FK_Algo_Comment_TO_Algo_Comment` FOREIGN KEY (`parent_id`) REFERENCES `Algo_Comment`(`id`),
  CONSTRAINT `FK_Member_TO_Algo_Comment` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 알고리즘 학습 진척도 (회원별 대분류별 진척도)
CREATE TABLE `Algo_Member_Progress` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `roadmap_id` INT NOT NULL,
  `count` INT NOT NULL,
  CONSTRAINT `FK_Member_TO_Algo_Member_Progress` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Algo_Roadmap_TO_Algo_Member_Progress` FOREIGN KEY (`roadmap_id`) REFERENCES `Algo_Roadmap`(`id`)
);

-- 코딩 문제 (문제 원본)
CREATE TABLE `Coding_Problem` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `platform` ENUM('BOJ','PGS','ETC') NOT NULL,
  `difficulty` VARCHAR(50) NOT NULL,
  `input` TEXT NULL,
  `output` TEXT NULL,
  `problem_url` VARCHAR(255) NOT NULL,
  `constraints` TEXT NULL,
  `post_count` INT NOT NULL DEFAULT 0,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_TO_Coding_Problem` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 코딩 풀이 게시물
CREATE TABLE `Coding_Post` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `problem_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  `ai_big_o` VARCHAR(100) NULL,
  `ai_good` VARCHAR(255) NULL,
  `ai_bad` VARCHAR(255) NULL,
  `ai_plan` VARCHAR(255) NULL,
  `comment_count` INT NOT NULL DEFAULT 0,
  `like_count` INT NOT NULL DEFAULT 0,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  CONSTRAINT `FK_Member_TO_Coding_Post` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Coding_Problem_TO_Coding_Post` FOREIGN KEY (`problem_id`) REFERENCES `Coding_Problem`(`id`)
);

-- 코딩 풀이 게시물 이미지
CREATE TABLE `Coding_Post_Image` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Coding_Post_TO_Coding_Post_Image` FOREIGN KEY (`post_id`) REFERENCES `Coding_Post`(`id`)
);

-- 코딩 풀이 댓글
CREATE TABLE `Coding_Comment` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id`  INT NOT NULL,
  `post_id` INT NOT NULL,
  `parent_id` INT NULL,
  `content` VARCHAR(500) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Coding_Post_TO_Coding_Comment` FOREIGN KEY (`post_id`) REFERENCES `Coding_Post`(`id`),
  CONSTRAINT `FK_Coding_Comment_TO_Coding_Comment` FOREIGN KEY (`parent_id`) REFERENCES `Coding_Comment`(`id`),
  CONSTRAINT `FK_MEMBER_TO_Coding_Comment` FOREIGN KEY (`member_id`) REFERENCES `Coding_Comment`(`id`)
);

-- 기업별 정보 공유 게시물
CREATE TABLE `Career_Info_Post` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `status` ENUM('NONE','PENDING','APPROVED','REJECTED') NOT NULL DEFAULT 'NONE',
  `reject_reason` VARCHAR(255) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  `content` TEXT NULL,
  `image_url` VARCHAR(255) NULL,
  `comment_count` INT NOT NULL DEFAULT 0,
  `like_count` INT NOT NULL DEFAULT 0,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_TO_Career_Info_Post` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 기업별 정보 공유 게시물 댓글
CREATE TABLE `Career_Info_Comment` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `parent_id` INT NULL,
  `post_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `content` VARCHAR(500) NOT NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  CONSTRAINT `FK_Career_Info_Comment_TO_Career_Info_Comment` FOREIGN KEY (`parent_id`) REFERENCES `Career_Info_Comment`(`id`),
  CONSTRAINT `FK_Career_Info_Post_TO_Career_Info_Comment` FOREIGN KEY (`post_id`) REFERENCES `Career_Info_Post`(`id`),
  CONSTRAINT `FK_Member_TO_Career_Info_Comment` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 추천 (공통: 알고리즘/코딩/커리어 게시물에 사용)
CREATE TABLE `Likes` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `algo_post_id` INT NULL,
  `coding_post_id` INT NULL,
  `career_info_post_id` INT NULL,
  CONSTRAINT `FK_Member_TO_Likes` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Algo_Post_TO_Likes` FOREIGN KEY (`algo_post_id`) REFERENCES `Algo_Post`(`id`),
  CONSTRAINT `FK_Coding_Post_TO_Likes` FOREIGN KEY (`coding_post_id`) REFERENCES `Coding_Post`(`id`),
  CONSTRAINT `FK_Career_Info_Post_TO_Likes` FOREIGN KEY (`career_info_post_id`) REFERENCES `Career_Info_Post`(`id`)
);

-- 스터디 모집글
CREATE TABLE `Study_Recruit_Post` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL COMMENT '스터디장',
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `start_date` VARCHAR(20) NOT NULL,
  `end_date` VARCHAR(20) NULL,
  `expires_at` VARCHAR(20) NOT NULL COMMENT '모집 마감 시각',
  `status` ENUM('OPEN','CLOSED','CANCELLED') NOT NULL DEFAULT 'OPEN',
  `capacity` INT NOT NULL DEFAULT 2 COMMENT '스터디장 포함 인원',
  `comment_count` INT NOT NULL DEFAULT 0,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Member_TO_Study_Recruit_Post` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 스터디 모집 댓글
CREATE TABLE `Study_Recruit_Comment` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `parent_id` INT NULL COMMENT '대댓글 self-reference',
  `content` VARCHAR(500) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Study_Recruit_Post_TO_Study_Recruit_Comment` FOREIGN KEY (`post_id`) REFERENCES `Study_Recruit_Post`(`id`),
  CONSTRAINT `FK_Member_TO_Study_Recruit_Comment` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Study_Recruit_Comment_TO_Study_Recruit_Comment` FOREIGN KEY (`parent_id`) REFERENCES `Study_Recruit_Comment`(`id`)
);

-- 스터디 모집 신청자
CREATE TABLE `Study_Recruit_Member` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `status` ENUM('PENDING','APPROVED','REJECTED') NOT NULL DEFAULT 'PENDING',
  CONSTRAINT `FK_Member_TO_Study_Recruit_Member` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Study_Recruit_Post_TO_Study_Recruit_Member` FOREIGN KEY (`post_id`) REFERENCES `Study_Recruit_Post`(`id`)
);

-- 스터디 공간
CREATE TABLE `Study` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  `start_date` VARCHAR(20) NOT NULL,
  `end_date` VARCHAR(20) NULL,
  CONSTRAINT `FK_Study_Recruit_Post_TO_Study` FOREIGN KEY (`post_id`) REFERENCES `Study_Recruit_Post`(`id`)
);

-- 스터디 참여자
CREATE TABLE `Study_Member` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `study_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `role` ENUM('LEADER','MEMBER') NOT NULL,
  CONSTRAINT `FK_Study_TO_Study_Member` FOREIGN KEY (`study_id`) REFERENCES `Study`(`id`),
  CONSTRAINT `FK_Member_TO_Study_Member` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`)
);

-- 스터디 게시물
CREATE TABLE `Study_Post` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `study_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `comment_count` INT NOT NULL DEFAULT 0,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Study_TO_Study_Post` FOREIGN KEY (`study_id`) REFERENCES `Study`(`id`),
  CONSTRAINT `FK_Study_Member_TO_Study_Post` FOREIGN KEY (`member_id`) REFERENCES `Study_Member`(`id`)
);

-- 스터디 게시물 이미지
CREATE TABLE `Study_Post_Image` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Study_Post_TO_Study_Post_Image` FOREIGN KEY (`post_id`) REFERENCES `Study_Post`(`id`)
);

-- 스터디 활동 댓글
CREATE TABLE `Study_Comment` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `post_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  `parent_id` INT NULL,
  `content` VARCHAR(500) NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  `updated_at` VARCHAR(20) NULL,
  `visibility` CHAR(1) NOT NULL DEFAULT 'Y',
  CONSTRAINT `FK_Study_Post_TO_Study_Comment` FOREIGN KEY (`post_id`) REFERENCES `Study_Post`(`id`),
  CONSTRAINT `FK_Study_Member_TO_Study_Comment` FOREIGN KEY (`member_id`) REFERENCES `Study_Member`(`id`),
  CONSTRAINT `FK_Study_Comment_TO_Study_Comment` FOREIGN KEY (`parent_id`) REFERENCES `Study_Comment`(`id`)
);

-- 스터디 로드맵
CREATE TABLE `Study_Roadmap` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `study_id` INT NOT NULL,  
  `member_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NULL,
  `order` INT NOT NULL,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Study_TO_Study_Roadmap` FOREIGN KEY (`study_id`) REFERENCES `Study`(`id`),
  CONSTRAINT `FK_Study_Member_TO_Study_Roadmap` FOREIGN KEY (`member_id`) REFERENCES `Study_Member`(`id`)
);

-- 스터디 마일스톤
CREATE TABLE `Study_Milestone` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `roadmap_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `order` INT NOT NULL,
  CONSTRAINT `FK_Study_Roadmap_TO_Study_Milestone` FOREIGN KEY (`roadmap_id`) REFERENCES `Study_Roadmap`(`id`)
);

-- 게시물/댓글 카테고리
CREATE TABLE `Report_Category` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL
);

-- 신고 유형
CREATE TABLE `Report_Type` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `type` VARCHAR(100) NOT NULL
);

-- 신고 내역
CREATE TABLE `Report` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `member_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `type_id` INT NOT NULL,
  `target_id` INT NOT NULL COMMENT '신고 대상 엔티티 PK',
  `content` VARCHAR(500) NULL,
  `created_at` VARCHAR(20) NOT NULL,
  CONSTRAINT `FK_Member_TO_Report` FOREIGN KEY (`member_id`) REFERENCES `Member`(`id`),
  CONSTRAINT `FK_Report_Category_TO_Report` FOREIGN KEY (`category_id`) REFERENCES `Report_Category`(`id`),
  CONSTRAINT `FK_Report_Type_TO_Report` FOREIGN KEY (`type_id`) REFERENCES `Report_Type`(`id`)
);