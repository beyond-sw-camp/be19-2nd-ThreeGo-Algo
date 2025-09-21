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